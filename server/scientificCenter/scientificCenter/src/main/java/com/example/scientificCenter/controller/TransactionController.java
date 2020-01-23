package com.example.scientificCenter.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.scientificCenter.domain.Transaction;
import com.example.scientificCenter.domain.TransactionStatus;
import com.example.scientificCenter.dto.TaskDTO;
import com.example.scientificCenter.dto.TransactionDTO;
import com.example.scientificCenter.service.JournalService;
import com.example.scientificCenter.service.ScientificAreaService;
import com.example.scientificCenter.service.TransactionService;
import com.example.scientificCenter.service.UserService;


@RestController
@RequestMapping("transaction")
@CrossOrigin(origins = "https://localhost:4202")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	private String address ="https://localhost:8086/kpService/paymentinfo";
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO tDTO)  {
		
		Transaction transaction= new Transaction();
		transaction.setAmount(tDTO.getAmount());
		transaction.setBuyerEmail(tDTO.getBuyerEmail());
		transaction.setJournalId(tDTO.getJournalId());
		transaction.setOrderId(UUID.randomUUID().toString());
		transaction.setErrorURL("https://localhost:4202/error");//+tDTO.getOrderId());
		transaction.setFailedURL("https://localhost:4202/failed");//+tDTO.getOrderId());
		transaction.setSuccessURL("https://localhost:4202/success");///"+tDTO.getOrderId());
		transaction.setStatus(TransactionStatus.CREATED);
		transaction.setMerchantIssn(tDTO.getMerchantIssn());
		this.transactionService.save(transaction);
		
		RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        TransactionDTO dto = new TransactionDTO(transaction);
        HttpEntity<TransactionDTO> entity = new HttpEntity<>(dto, headers);
        
        try {
        	temp.postForObject(address+"/createFromNC", dto, Transaction.class);
            System.out.println("Successfull created payment!");
            
        } catch (HttpStatusCodeException exception) {
            System.out.println("Error while creating payment - HttpStatusCodeException!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
	
	
	
}