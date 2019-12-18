package com.example.bitcoinService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bitcoinService.MyWallet;

@RefreshScope
@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
    private MyWallet myWallet;

    @RequestMapping
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/send")
    public String send(@RequestParam String amount, @RequestParam String address) {
        myWallet.send(amount, address);
        return "Done!";
    }
    
    @Value("${proba}")
	private String proba;

	@GetMapping("/1")
	public String getHello() {
		return proba;
	}


}
