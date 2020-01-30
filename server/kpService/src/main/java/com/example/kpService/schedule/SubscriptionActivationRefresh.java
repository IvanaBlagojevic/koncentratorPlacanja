package com.example.kpService.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.kpService.domain.Merchant;
import com.example.kpService.domain.Subscription;
import com.example.kpService.service.MerchantService;
import com.example.kpService.service.SubscriptionService;

@EnableScheduling
@Component
public class SubscriptionActivationRefresh {

	@Autowired
	private SubscriptionService subService;
	
	@Autowired
	private MerchantService merchService;
	
	@Scheduled(fixedRate = 60000)
	public void refreshActivateField() {
		
		List<Subscription> subList = subService.getAll();
		
		for(Subscription s : subList) {
			
			Merchant merchant = merchService.getByUsername(s.getMerchantUsername());
			
			RestTemplate template = new RestTemplate();
			
			try {
				
				String ret = template.getForObject("https://localhost:8086/payPalService/getAgrStatus/"+s.getAgreementId()+"/"+s.getMerchantUsername(),
						 String.class);
				System.out.println("Ret je: " + ret);
				if(ret.equals("Cancelled"))
				{
					//kp
					s.setActive(false);
					subService.saveSub(s);
					
					//nc
					try {
						template.getForEntity(merchant.getSystem().getBackUrl()+"/subscription/changeActivity/"+s.getMerchantUsername()+"/"+s.getSubEmail(),
								null, String.class);
					}catch(HttpStatusCodeException e1)
					{
						System.out.println("Greska prilikom menjanja statusa u NC");
					}
					
				}
				
			}catch (HttpStatusCodeException e) {
				// TODO: handle exception
				System.out.println("Greska prilikom uzimanja statusa");
			}
		}
	}
}
