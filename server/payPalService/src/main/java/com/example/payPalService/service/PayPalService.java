package com.example.payPalService.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.payPalService.domain.Order;
import com.example.payPalService.domain.OrderState;
import com.example.payPalService.domain.PaymentStatus;
import com.example.payPalService.domain.UserPayPal;
import com.example.payPalService.dto.PaymentDTO;
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
	
	//podaci business account-a, koji je trgovac, a logujem se na personal account prilikom uplate (kupac)
	public String clientId = "AboVa2o5REedvjt-AO-30c84Mken1J-bQ5_YgoOWd1lJzFgDt8GzVo5OG9omfgae8idotWlLmXk1tHNQ";
	public String clientSecret = "EFA16-8S9eYP1i7GTtkgEcZLrS30UWCCN90Hm4EHqx33KBCC-7ZHrIzFiNH-cLW9cUl6oLxzGaxLttpx";

	public Map<String,Object> createPayment(PaymentDTO paymentDTO){
		
		//dobavljanje odgovarajuce korisnika
		UserPayPal user = userService.getUserByUsername(paymentDTO.getMerchantEmail());
		Amount amount = new Amount("USD",Double.toString(paymentDTO.getAmount())); //kreira se objekat koji nosi info o valuti i iznosu
								//par dva je iznos koji se isplacuje od strane payer-a
		
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
		
		RedirectUrls redirectUrl = new RedirectUrls();
		redirectUrl.setCancelUrl("https://localhost:1234/error");
	    //redirectUrl.setReturnUrl("https://localhost:8087/paypal/complete?username="+username); 
		redirectUrl.setReturnUrl("https://localhost:1234/ppsuccess?username="+user.getUsername()); //iz fronta kp se mora redirektovati na complete metodu
	    payment.setRedirectUrls(redirectUrl);
	    
	    Payment createdPayment;
	    Map<String, Object> response = new HashMap<String, Object>();
	    try 
	    {
	    	String redirectionUrl = ""; //podaci o trgovcu
	    	APIContext context = new APIContext(user.getClientId(), user.getClientSecret(), "sandbox");
	    	createdPayment = payment.create(context);
	    	
	    	//sacuvati porudzbinuuuuuuu
	    	double a =   Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal());
	    	
	    	Date today = new Date();
	    	
	    	Order order = new Order(createdPayment.getId(),paymentDTO.getMerchantEmail(),paymentDTO.getAmount()
	    			,PaymentStatus.CREATED,OrderState.NOT_RETURNED,today,null,"https://localhost:1234/error");
	    	this.orderService.saveOrder(order);
	    	
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
	        }
	    }catch (PayPalRESTException e) {
	        System.out.println("Error happened during payment creation!");
	    }
		
		return response;
	}
	
	public Map<String, Object> completePayment(String idPayment, String idPayer, String username){
		
		UserPayPal user = userService.getUserByUsername(username);
		
		Map<String, Object> response = new HashMap<>();
		Order order;
		
		Payment payment = new Payment();
		payment.setId(idPayment);
		
		PaymentExecution payExecution = new PaymentExecution();
		payExecution.setPayerId(idPayer);
		
		try {
			
			APIContext context = new APIContext(user.getClientId(),user.getClientSecret(),"sandbox");
			Payment createdPayment = payment.execute(context, payExecution); //izvrsavanje bi trebalo da je ovoooooo
			
			if(createdPayment != null)
			{
				order = this.orderService.getByPaymentId(idPayment).get();
				order.setStatus(PaymentStatus.APPROVED);
				order.setCompleteDate(new Date());
				
				this.orderService.saveOrder(order);
				response.put("status", "success");
				response.put("payment", createdPayment);
			}
			
		}catch(PayPalRESTException e) {
			
			throw new BadRequest("Error happened during payment complete");
		}
		
		return response;
	}
	
}

