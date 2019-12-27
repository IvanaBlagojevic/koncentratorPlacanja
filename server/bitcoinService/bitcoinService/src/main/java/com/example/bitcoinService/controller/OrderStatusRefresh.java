package com.example.bitcoinService.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.bitcoinService.domain.BitcoinUser;
import com.example.bitcoinService.domain.MyOrder;
import com.example.bitcoinService.domain.OrderStatusEnum;
import com.example.bitcoinService.dto.OrderPaidDTO;
import com.example.bitcoinService.dto.PaymentResponseDTO;
import com.example.bitcoinService.services.BitcoinUserService;
import com.example.bitcoinService.services.OrderService;

@EnableScheduling
@Component
public class OrderStatusRefresh {

	@Autowired
	OrderService os;
	
	@Autowired
	BitcoinUserService bus;
	
	private static final Logger logger  = LoggerFactory.getLogger(OrderStatusRefresh.class);

	
	@Scheduled(fixedRate = 60000)
	public void refreshOrderStatus() {
		
		List<MyOrder> orders = os.findAll();
		for (MyOrder o : orders) {
			
			BitcoinUser bu = bus.findOneByUsername(o.getUsername());
			if (bu == null) {
				System.out.println("Merchant with this username does not exists!");
				logger.error(" 6 41 4 1");
				return;
			}
			boolean fleg = false;
			
			RestTemplate temp = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
			headers.add("Authorization", "Bearer "+bu.getToken());
			HttpEntity<OrderPaidDTO> request = new HttpEntity<>(headers);
			
			try{
				ResponseEntity<OrderPaidDTO> response = temp.exchange("https://api-sandbox.coingate.com/v2/orders/"+o.getPaymentId(), HttpMethod.GET, request, OrderPaidDTO.class);
			
				if (response.getBody().getStatus().equals("canceled")) {
					o.setStatus(OrderStatusEnum.CANCELED);
					fleg = true;
				}else if (response.getBody().getStatus().equals("paid")) {
					o.setStatus(OrderStatusEnum.PAID);
					fleg = true;
				}else if (response.getBody().getStatus().equals("expired")) {
					o.setStatus(OrderStatusEnum.EXPIRED);
					fleg = true;
				}else if (response.getBody().getStatus().equals("invalid")) {
					o.setStatus(OrderStatusEnum.INVALID);
					fleg = true;
				}else if (response.getBody().getStatus().equals("pending")) {
					o.setStatus(OrderStatusEnum.PENDING);
					fleg = true;
				}else if (response.getBody().getStatus().equals("confirming")) {
					o.setStatus(OrderStatusEnum.CONFIRMING);
					fleg = true;
				}else if (response.getBody().getStatus().equals("refunded")) {
					o.setStatus(OrderStatusEnum.REFUNDED);
					fleg = true;
				}
				
				if (fleg == true) {
					o.setUpdated(new Date());
					os.save(o);
					logger.info(" 6 42 4 0");
				}
			}catch(HttpStatusCodeException e) {
				logger.info(" 6 42 4 1");
			}
			
			
			
		}
		
		
		
	}
}
