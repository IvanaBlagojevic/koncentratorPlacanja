package com.example.bitcoinService.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bitcoinService.domain.BitcoinUser;
import com.example.bitcoinService.domain.MyOrder;
import com.example.bitcoinService.domain.OrderStatusEnum;
import com.example.bitcoinService.dto.BitcoinUserDTO;
import com.example.bitcoinService.dto.OrderPaidDTO;
import com.example.bitcoinService.dto.PaymentRequestDTO;
import com.example.bitcoinService.dto.PaymentResponseDTO;
import com.example.bitcoinService.services.BitcoinUserService;
import com.example.bitcoinService.services.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = "https://localhost:1234")
public class BitcoinController {
	
	@Autowired
	OrderService os;
	
	@Autowired
	BitcoinUserService bus;
	
	private static final Logger logger  = LoggerFactory.getLogger(BitcoinController.class);
	
	 @RequestMapping(value="/getOne/{oid}", method = RequestMethod.GET)
	 public ResponseEntity<Map<String, Object>> getPayment(@PathVariable("oid") Long oid) {
		 	System.out.println("usao u getOne payment " + oid);
		 	MyOrder o = os.findOneByPaymentId(Long.toString(oid));
		 	if (o == null) {
		 		System.out.println("Order with this oid does not exists!");
		 		logger.error(" 6 31 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		
		 	String isPaid="";
		 	//NEW, PENDING, CONFIRMING, PAID, INVALID, CANCELED, REFUNDED, EXPIRED - bitcoin statusi
		 	if (o.getStatus() == OrderStatusEnum.NEW || o.getStatus() == OrderStatusEnum.PENDING || o.getStatus() == OrderStatusEnum.CONFIRMING ) {
		 		isPaid = "new";
		 	}else if (o.getStatus() == OrderStatusEnum.PAID) {
		 		isPaid = "paid";
		 	}else if (o.getStatus() == OrderStatusEnum.INVALID) {
		 		isPaid = "invalid";
		 	}else if (o.getStatus() == OrderStatusEnum.CANCELED || o.getStatus() == OrderStatusEnum.EXPIRED) {
		 		isPaid = "unfinished";
		 	}
	        
		 	Map<String, Object> mapToKP = new HashMap<String, Object>();
			mapToKP.put("merchantIssn", o.getUsernameIssn());
			mapToKP.put("userEmail", "ivana");								//zakucano
			mapToKP.put("orderNumberId", o.getPaymentId());
			mapToKP.put("isPaid", isPaid);
			mapToKP.put("paymentMethod", "bitcoinService");
			mapToKP.put("created", o.getCreated());
			//mapToKP.put("updated", o.getCreated());
			
		 	
		 	return new ResponseEntity<Map<String, Object>>(mapToKP, HttpStatus.ACCEPTED);
	 }


	@RequestMapping(value="/create", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
    public String send(@RequestBody PaymentRequestDTO pr) {
		String payFormUrl = "";
		BitcoinUser bu = bus.findOneByUsername(pr.getMerchantIssn());
		if (bu == null) {
			System.out.println("Merchant with this username does not exists!");
	 		//dodati ko je izvrsio-koji nalog usera 6 1 ko
			logger.error(" 6 11 4 1");
			return null;
		}
		
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Bearer "+bu.getToken());
		
		String uniqueID = UUID.randomUUID().toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_id", uniqueID);
		map.put("price_amount", pr.getAmount());
		map.put("price_currency", "USD");
		map.put("receive_currency", "USD");
		map.put("title", "bitcoin payment");
		map.put("description", "bitcoin payment");
		map.put("callback_url","https://localhost:1234");
		map.put("cancel_url", "https://localhost:1234/b/bitcoinCancel/"+uniqueID);
		map.put("success_url", "https://localhost:1234/b/bitcoinSuccess/"+uniqueID);
		
		HttpEntity<Map<String,Object>> request = new HttpEntity<>(map,headers);
		
		try{
			PaymentResponseDTO response = temp.postForObject("https://api-sandbox.coingate.com/v2/orders", request, PaymentResponseDTO.class);
			payFormUrl = response.getPayment_url();
			MyOrder o = new MyOrder (response.getId().toString(), bu.getUsername(), new Date(), null, Double.parseDouble(response.getPrice_amount()), 
					response.getPrice_currency(), OrderStatusEnum.NEW , uniqueID);
			MyOrder saved = os.save(o);
			
			RestTemplate toKP = new RestTemplate();
			HttpHeaders headersToKP = new HttpHeaders();
			Map<String, Object> mapToKP = new HashMap<String, Object>();
			mapToKP.put("merchantIssn", pr.getMerchantIssn());
			mapToKP.put("userEmail", "ivana");								//zakucano
			mapToKP.put("orderNumberId", response.getId().toString());
			mapToKP.put("isPaid", "CREATED");
			mapToKP.put("paymentMethod", "bitcoinService");
			mapToKP.put("created", o.getCreated());
			//mapToKP.put("updated", o.getCreated());
			HttpEntity<Map<String,Object>> requesttoKP = new HttpEntity<>(mapToKP, headersToKP);
			toKP.postForEntity("https://localhost:8086/kpService/paymentinfo/create", requesttoKP, PaymentResponseDTO.class);
			
			logger.info(" 6 12 4 0");
		}catch(HttpStatusCodeException e) {
			logger.info(" 6 12 4 1");
		}
		
		
        return payFormUrl;
    }
	
	
	 @RequestMapping(value="/completePayment/{oid}", method = RequestMethod.GET)
	 public ResponseEntity complatePayment(@PathVariable("oid") String oid) {
		 	System.out.print("usao u compleate payment");
		 	MyOrder o = os.findOneByRandomUniqueID(oid);
		 	if (o == null) {
		 		System.out.println("Order with this oid does not exists!");
		 		logger.error(" 6 21 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	if (o.getStatus() == OrderStatusEnum.CANCELED || o.getStatus() == OrderStatusEnum.PAID) {
		 		System.out.println("Order with this oid is already canceld or paid!");
		 		logger.error(" 6 22 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	BitcoinUser bu = bus.findOneByUsername(o.getUsernameIssn());
		 	if (bu == null) {
		 		System.out.println("Merchant with this username does not exists!");
		 		logger.error(" 6 23 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 
		 	RestTemplate temp = new RestTemplate();
		 	HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("Authorization", "Bearer "+bu.getToken());
			HttpEntity<PaymentResponseDTO> request = new HttpEntity<>(headers);
			
		 	try{
		 		ResponseEntity<PaymentResponseDTO> response = temp.exchange("https://api-sandbox.coingate.com/v2/orders/"+o.getPaymentId(), HttpMethod.GET, request, PaymentResponseDTO.class);
		 		if (response.getBody().getStatus().equals("paid")) {
		        	o.setStatus(OrderStatusEnum.PAID);
		        	o.setUpdated(new Date());
		        	os.save(o);
		        	logger.info(" 6 24 4 0");
		        	
		        	RestTemplate toKP = new RestTemplate();
					HttpHeaders headersToKP = new HttpHeaders();
					Map<String, Object> mapToKP = new HashMap<String, Object>();
					HttpEntity<Map<String,Object>> requesttoKP = new HttpEntity<>(mapToKP, headersToKP);
					toKP.postForEntity("https://localhost:8086/kpService/paymentinfo/update/"+o.getPaymentId()+"/true/Bitcoin", requesttoKP, PaymentResponseDTO.class);
		        }
		 	}catch(HttpStatusCodeException e) {
		 		logger.info(" 6 24 4 1");
		 	}
	        
		 	
		 	return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	 }
	
	 @RequestMapping(value="/cancelPayment/{oid}", method = RequestMethod.GET)
	 public ResponseEntity cancelPayment(@PathVariable("oid") String oid) {
		 	System.out.print("usao u cancel payment");
		 	MyOrder o = os.findOneByRandomUniqueID(oid);
		 	if (o == null) {
		 		System.out.println("Order with this oid does not exists!");
		 		logger.error(" 6 31 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	if (o.getStatus() == OrderStatusEnum.CANCELED || o.getStatus() == OrderStatusEnum.PAID) {
		 		System.out.println("Order with this oid is already canceld or paid!");
		 		logger.error(" 6 32 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	BitcoinUser bu = bus.findOneByUsername(o.getUsernameIssn());
		 	if (bu == null) {
		 		System.out.println("Merchant with this username does not exists!");
		 		logger.error(" 6 33 4 1");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 
		 	RestTemplate temp = new RestTemplate();
		 	HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("Authorization", "Bearer "+bu.getToken());
			HttpEntity<PaymentResponseDTO> request = new HttpEntity<>(headers);
			
		 	try{
		 		ResponseEntity<PaymentResponseDTO> response = temp.exchange("https://api-sandbox.coingate.com/v2/orders/"+o.getPaymentId(), HttpMethod.GET, request, PaymentResponseDTO.class);
		 		if (response.getBody().getStatus().equals("canceled")) {
		        	o.setStatus(OrderStatusEnum.CANCELED);
		        	o.setUpdated(new Date());
		        	os.save(o);
		        	
		        	RestTemplate toKP = new RestTemplate();
					HttpHeaders headersToKP = new HttpHeaders();
					Map<String, Object> mapToKP = new HashMap<String, Object>();
					HttpEntity<Map<String,Object>> requesttoKP = new HttpEntity<>(mapToKP, headersToKP);
					toKP.postForEntity("https://localhost:8086/kpService/paymentinfo/update/"+o.getPaymentId()+"/false/Bitcoin", requesttoKP, PaymentResponseDTO.class);
		        	
		        	logger.info(" 6 34 4 0");
		        }
		 		
		 	}catch(HttpStatusCodeException e) {
		 		logger.info(" 6 34 4 1");
		 	}
	        
		 	
		 	return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	 }
	
	 
	 /////////////////metoda za dodavanje novog user-a, nakon registracije merchant-a u kp-u////////////////
	 @RequestMapping(
				path="addUser",
				method = RequestMethod.POST)
		public ResponseEntity addNewUser(@RequestBody BitcoinUserDTO user) {
		 	System.out.println("Dodavanje korisnika u BitcoinController");
		 	System.out.println("token " + user.getToken());
		 	BitcoinUser bu = new BitcoinUser(user.getUsername(), user.getToken());
		 	
		 	bus.save(bu);
		 
			return new ResponseEntity<>("User added to bitcoin!",HttpStatus.OK);
			
		}
	
}
