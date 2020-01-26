package com.example.eurekaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;

@RestController
@RequestMapping("test")
public class TestController {
	
	@RequestMapping(value="/instances", method = RequestMethod.GET)
	public Map<String,String> getApplications() {
		Map<String,String> back = new HashMap<>();
		
		PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
	    Applications applications = registry.getApplications();

	    applications.getRegisteredApplications().forEach((registeredApplication) -> {
	        registeredApplication.getInstances().forEach((instance) -> {
	        	back.put(instance.getInstanceId(), instance.getAppName());
	            System.out.println(instance.getAppName() + " (" + instance.getInstanceId() + ") : ");
	        });
	    });
	    return back;
	}
		
	 @RequestMapping(value="/hello", method = RequestMethod.GET)
	 public ResponseEntity<String> hello() {
		 System.out.println("Pogodio");
		 return new ResponseEntity<>("Hello World!! You are seeing this only because I TRUST YOU!!!", HttpStatus.OK);
	 
	 }
	
}
