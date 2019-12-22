package com.example.bankService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankService.domain.Merchant;
import com.example.bankService.dto.MerchantDTO;
import com.example.bankService.dto.PaymentDTO;
import com.example.bankService.service.MerchantService;
import com.example.bankService.service.PaymentService;

@RefreshScope
@RestController
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private PaymentService paymentService;
	
	
	@RequestMapping(value = "/add",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity paying(@RequestBody PaymentDTO pdto) {

        String paymentUrl = this.paymentService.createPayment(pdto);

        return new ResponseEntity<String>(paymentUrl, HttpStatus.OK);
    }

   /* @GetMapping(
            path = "/success/{token}",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity success(@PathVariable("token") String token) {

        String retVal = this.paymentCardService.completePayment(token);

        return new ResponseEntity<>(retVal, HttpStatus.FOUND);
    }

    @GetMapping(
            path = "/error/{token}",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity error(@PathVariable("token") String token) {

        String retVal = this.paymentCardService.errorPayment(token);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(
            path = "/failed/{token}",
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity failed(@PathVariable("token") String token) {

        String retVal = this.paymentCardService.failedPayment(token);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
*/
}
