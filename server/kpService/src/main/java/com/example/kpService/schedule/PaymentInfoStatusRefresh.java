package com.example.kpService.schedule;

import static java.util.concurrent.TimeUnit.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.kpService.domain.PaymentInfo;
import com.example.kpService.domain.PaymentStatus;
import com.example.kpService.service.PaymentInfoService;
import com.fasterxml.jackson.databind.JsonNode;


@EnableScheduling
@Component
public class PaymentInfoStatusRefresh {

	@Autowired
	PaymentInfoService pis;
	
	private static final Logger logger  = LoggerFactory.getLogger(PaymentInfoStatusRefresh.class);

	//30min je 1800000
	@Scheduled(fixedRate = 60000)
	public void refreshOrderStatus() {
		
		List<PaymentInfo> orders = pis.findAllByIsPaid(PaymentStatus.CREATED);
		for (PaymentInfo o : orders) {
			
			String method = o.getPaymentMethod();
			System.out.println("Metod placanja " + method);
			
			long MAX_DURATION = MILLISECONDS.convert(30, MINUTES);
			Date now = new Date();
			long duration = now.getTime() - o.getCreated().getTime();
			System.out.println("duration " + duration);

			if (duration >= MAX_DURATION) {
				o.setUpdated(now);
				o.setPaid(PaymentStatus.UNFINISHED);
			}else {
			
				RestTemplate toMS = new RestTemplate();
				HttpHeaders headersToMS = new HttpHeaders();
				Map<String, Object> mapToMS = new HashMap<String, Object>();
				HttpEntity<Map<String,Object>> requesttoMS = new HttpEntity<>(mapToMS, headersToMS);
				
				
				ResponseEntity<JsonNode> response = toMS.exchange("https://localhost:8086/"+ method + "/getOne/"+o.getOrderNumberId(),HttpMethod.GET, requesttoMS, JsonNode.class);
				JsonNode map = response.getBody();
				System.out.println("odgovor " + map);
				//CREATED, PAID, ERROR, UNFINISHED, FAILED
		
				String compare = map.get("isPaid").asText();
				System.out.println("compare " + compare);
				if (compare.equals("new")) {
					o.setPaid(PaymentStatus.CREATED);
					o.setUpdated(now);
				}else if (compare.equals("paid")) {
					o.setPaid(PaymentStatus.PAID);
					o.setUpdated(now);
				}else if (compare.equals("invalid")) {
					o.setPaid(PaymentStatus.ERROR);
					o.setUpdated(now);
				}else if (compare.equals("unfinished")) {
					o.setPaid(PaymentStatus.UNFINISHED);
					o.setUpdated(now);
				}
			}
			
			pis.save(o);
			
		}
		
		
		
	}
}