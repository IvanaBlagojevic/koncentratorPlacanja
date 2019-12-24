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
import org.springframework.web.client.RestTemplate;

import com.example.bitcoinService.domain.BitcoinUser;
import com.example.bitcoinService.domain.MyOrder;
import com.example.bitcoinService.domain.OrderStatusEnum;
import com.example.bitcoinService.dto.OrderPaidDTO;
import com.example.bitcoinService.dto.PaymentRequestDTO;
import com.example.bitcoinService.dto.PaymentResponseDTO;
import com.example.bitcoinService.services.BitcoinUserService;
import com.example.bitcoinService.services.OrderService;

@RestController
@RequestMapping("bitcoin")
@CrossOrigin(origins = "https://localhost:1234")
public class BitcoinController {
	
	@Autowired
	OrderService os;
	
	@Autowired
	BitcoinUserService bus;


	@RequestMapping(value="/createPayment", method = RequestMethod.POST, produces="text/plain")
	@ResponseBody
    public String send(@RequestBody PaymentRequestDTO pr) {
		String payFormUrl = "";
		BitcoinUser bu = bus.findOneByUsername(pr.getMerchantEmail());
		if (bu == null) {
			System.out.println("Merchant with this username does not exists!");
	 		return null;
		}
		
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Authorization", "Bearer "+bu.getToken());
		
		String uniqueID = UUID.randomUUID().toString();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_id", pr.getMerchantOrderId().toString());
		map.put("price_amount", pr.getAmount());
		map.put("price_currency", pr.getCurrency());
		map.put("receive_currency", pr.getCurrency());
		map.put("title", "bitcoin payment");
		map.put("description", "bitcoin payment");
		map.put("callback_url","https://localhost:1234");
		map.put("cancel_url", "https://localhost:1234/bitcoinCancel/"+uniqueID);
		map.put("success_url", "https://localhost:1234/bitcoinSuccess/"+uniqueID);
		
		HttpEntity<Map<String,Object>> request = new HttpEntity<>(map,headers);
		PaymentResponseDTO response = temp.postForObject("https://api-sandbox.coingate.com/v2/orders", request, PaymentResponseDTO.class);
		payFormUrl = response.getPayment_url();
		System.out.println(payFormUrl);
		
		MyOrder o = new MyOrder (response.getId().toString(), bu.getUsername(), new Date(), null, Double.parseDouble(response.getPrice_amount()), 
				response.getPrice_currency(), OrderStatusEnum.NEW , uniqueID);
		//id from coinbase
		//o.setPaymentId(response.getId().toString());
		//username merchant
		System.out.println("bu username " + bu.getUsername());
		o.setUsername(bu.getUsername());
		o.setStatus(OrderStatusEnum.NEW);
		
		MyOrder saved = os.save(o);
		
        return payFormUrl;
    }
	
	
	 @RequestMapping(value="/completePayment/{oid}", method = RequestMethod.GET)
	 public ResponseEntity complatePayment(@PathVariable("oid") String oid) {
		 	System.out.print("usao u compleate payment");
		 	MyOrder o = os.findOneByRandomUniqueID(oid);
		 	if (o == null) {
		 		System.out.println("Order with this oid does not exists!");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	if (o.getStatus() == OrderStatusEnum.CANCELED || o.getStatus() == OrderStatusEnum.PAID) {
		 		System.out.println("Order with this oid is already canceld or paid!");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	BitcoinUser bu = bus.findOneByUsername(o.getUsername());
		 	if (bu == null) {
		 		System.out.println("Merchant with this username does not exists!");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 
		 	RestTemplate temp = new RestTemplate();
		 	HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("Authorization", "Bearer "+bu.getToken());
			HttpEntity<PaymentResponseDTO> request = new HttpEntity<>(headers);
			
		 	ResponseEntity<PaymentResponseDTO> response = temp.exchange("https://api-sandbox.coingate.com/v2/orders/"+o.getPaymentId(), HttpMethod.GET, request, PaymentResponseDTO.class);
	        if (response.getBody().getStatus().equals("paid")) {
	        	o.setStatus(OrderStatusEnum.PAID);
	        	o.setUpdated(new Date());
	        	os.save(o);
	        }
		 	
		 	return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	 }
	
	 @RequestMapping(value="/cancelPayment/{oid}", method = RequestMethod.GET)
	 public ResponseEntity cancelPayment(@PathVariable("oid") String oid) {
		 	System.out.print("usao u cancel payment");
		 	MyOrder o = os.findOneByRandomUniqueID(oid);
		 	if (o == null) {
		 		System.out.println("Order with this oid does not exists!");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	if (o.getStatus() == OrderStatusEnum.CANCELED || o.getStatus() == OrderStatusEnum.PAID) {
		 		System.out.println("Order with this oid is already canceld or paid!");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 	
		 	BitcoinUser bu = bus.findOneByUsername(o.getUsername());
		 	if (bu == null) {
		 		System.out.println("Merchant with this username does not exists!");
		 		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		 	}
		 
		 	RestTemplate temp = new RestTemplate();
		 	HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("Authorization", "Bearer "+bu.getToken());
			HttpEntity<PaymentResponseDTO> request = new HttpEntity<>(headers);
			
		 	ResponseEntity<PaymentResponseDTO> response = temp.exchange("https://api-sandbox.coingate.com/v2/orders/"+o.getPaymentId(), HttpMethod.GET, request, PaymentResponseDTO.class);
	        if (response.getBody().getStatus().equals("canceled")) {
	        	o.setStatus(OrderStatusEnum.CANCELED);
	        	o.setUpdated(new Date());
	        	os.save(o);
	        }
		 	
		 	return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	 }
	
}
