package com.example.payPalService.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.Spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


import com.example.payPalService.domain.Order;
import com.example.payPalService.domain.OrderStatus;
import com.example.payPalService.domain.UserPayPal;
import com.example.payPalService.dto.PaymentDTO;
import com.example.payPalService.dto.PaymentInfoDTO;
import com.example.payPalService.exceptions.BadRequest;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Authorization;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;


@Service
public class PayPalService {
	
	@Autowired
	private UserPayPalService userService;
	
	@Autowired
	private OrderService orderService;
	
	private static final Logger logger  = LoggerFactory.getLogger(PayPalService.class);
	
	public Map<String,Object> createPayment(PaymentDTO paymentDTO){
		
		//dobavljanje odgovarajuce korisnika
		UserPayPal user = userService.getUserByUsername(paymentDTO.getMerchantIssn());
		Amount amount = new Amount("USD",Double.toString(paymentDTO.getAmount())); //kreira se objekat koji nosi info o valuti i iznosu
								
		
		Transaction transaction = new Transaction(); //sadzi sve potrebne info o transakciji
		transaction.setAmount(amount);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");	//setovanje metode placanja
		
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		
		Order order = new Order();
		this.orderService.saveOrder(order);
		
		RedirectUrls redirectUrl = new RedirectUrls();
		redirectUrl.setCancelUrl("https://localhost:1234/ppcancel?oid="+order.getId()); 
		redirectUrl.setReturnUrl("https://localhost:1234/ppsuccess?username="+user.getUsername());
	    payment.setRedirectUrls(redirectUrl);
	    
	    Payment createdPayment;
	    Map<String, Object> response = new HashMap<String, Object>();
	    try 
	    {
	    	String redirectionUrl = ""; 
	    	APIContext context = new APIContext(user.getClientId(), user.getClientSecret(), "sandbox");
	    	createdPayment = payment.create(context);
	    	
	    	
	    	double a =   Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal());
	    	
	    	Date today = new Date();
	    	
	    	order.setPaymentId(createdPayment.getId());
	    	order.setMerchant(paymentDTO.getMerchantIssn());
	    	order.setAmount(paymentDTO.getAmount());
	    	order.setStatus(OrderStatus.CREATED);
	    	order.setCreationDate(today);
	    	order.setCallbackUrl("https://localhost:4202/error");
	    	Order ret = this.orderService.saveOrder(order);
	    	
	    	//slanje podataka o placanju kpServisu 
	    	//System.out.println("Broooj " + ret.getId());
	    	
	    	PaymentInfoDTO info = new PaymentInfoDTO(paymentDTO.getMerchantIssn(), "mejl", ret.getId(), OrderStatus.CREATED, "payPalService", new Date(), null);    	
	    	RestTemplate template = new RestTemplate();
	    	HttpHeaders header = new HttpHeaders();
	    	header.setContentType(MediaType.APPLICATION_JSON);
	    	HttpEntity<PaymentInfoDTO> request = new HttpEntity<>(info,header);
	    	
			try {
				template.postForEntity("https://localhost:8086/kpService/paymentinfo/create", request, PaymentInfoDTO.class);
			}catch(HttpStatusCodeException e) {
				e.printStackTrace();
			}
	    	
	    	if(createdPayment!=null){
	            List<Links> links = createdPayment.getLinks();
	            for (Links link:links) {
	                if(link.getRel().equals("approval_url")){
	                	redirectionUrl = link.getHref(); //ako je uspesno kreirana
	                									//ici ce preusmerenje na complete endpoint
	                    break;
	                }
	            }
	            response.put("status", "success");
	            response.put("redirect_url", redirectionUrl);
	            //fali ko - 5 1 ko
	            logger.info("5 1 4 0");
	        }
	    }catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	        logger.info("5 1 4 1");
	    }
		
		return response;
	}
	
	public Map<String, Object> completePayment(String idPayment, String idPayer, String username){
		
		UserPayPal user = userService.getUserByUsername(username);
		
		Map<String, Object> response = new HashMap<>();
		Order order;
		order = this.orderService.getByPaymentId(idPayment).get();
		
		Payment payment = new Payment();
		payment.setId(idPayment);
		
		PaymentExecution payExecution = new PaymentExecution();
		payExecution.setPayerId(idPayer);
		
		try {
			
			APIContext context = new APIContext(user.getClientId(),user.getClientSecret(),"sandbox");
			payment.execute(context, payExecution); 
			//order = this.orderService.getByPaymentId(idPayment).get();
			
			
		}catch(PayPalRESTException e) {
			logger.info("5 2 4 0");
			throw new BadRequest("Error happened during payment complete");
		}
		
		order.setUpdateDate(new Date());
		order.setStatus(OrderStatus.PAYED);
		this.orderService.saveOrder(order);
		
		//slanje podataka o placanju kpServisu 	    	
    	RestTemplate template = new RestTemplate();
    	HttpHeaders header = new HttpHeaders();
    	header.setContentType(MediaType.APPLICATION_JSON);
		
    	try {
    		template.put("https://localhost:8086/kpService/paymentinfo/update/"+order.getId()+"/true/payPalService", PaymentInfoDTO.class);
    	}catch(HttpStatusCodeException e)
    	{
    		e.printStackTrace();
    	}
		
		logger.info("5 2 4 1");
	    response.put("redirect_url", "https://localhost:4202/success");
		
		return response;
	}
	
	public String changePaymentStatusToCanceled(Long orderId) {
		
		Order order = this.orderService.getById(orderId).get();
		
		order.setUpdateDate(new Date());
		order.setStatus(OrderStatus.CANCELED);
		this.orderService.saveOrder(order);
		
		return order.getCallbackUrl();
	}

	public Map<String, Object> getByOrderId(Long oid) {
		Optional<Order> or = orderService.getById(oid);
	 	if (or == null) {
	 		System.out.println("Order with this oid does not exists!");
	 		return null;
	 	}
	 	
	 	String isPaid="";
	 	Order o = or.get();
	 	if (o.getStatus() == OrderStatus.CREATED ) {
	 		isPaid = "new";
	 	}else if (o.getStatus() == OrderStatus.PAYED) {
	 		isPaid = "paid";
	 	}else if (o.getStatus() == OrderStatus.CANCELED) {
	 		isPaid = "unfinished";
	 	}
	 	
	 	Map<String, Object> mapToKP = new HashMap<String, Object>();
		mapToKP.put("merchantIssn", o.getMerchant());
		mapToKP.put("userEmail", "ivana");								//zakucano
		mapToKP.put("orderNumberId", o.getPaymentId());
		mapToKP.put("isPaid", isPaid);
		mapToKP.put("paymentMethod", "payPalService");
		mapToKP.put("created", o.getCreationDate());
		//mapToKP.put("updated", o.getCreated());
	
		return mapToKP;
	}
	
}

