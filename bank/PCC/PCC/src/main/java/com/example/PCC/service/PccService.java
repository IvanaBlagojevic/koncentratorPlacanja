package com.example.PCC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.PCC.domain.Bank;
import com.example.PCC.domain.IssuerResponse;
import com.example.PCC.domain.Request;
import com.example.PCC.dto.InterbankRequestDTO;
import com.example.PCC.dto.IssuerResponseDTO;
import com.example.PCC.dto.RequestDTO;
import com.example.PCC.repository.BankRepository;
import com.example.PCC.repository.IssuerResponseRepository;
import com.example.PCC.repository.RequestRepository;


@Service
public class PccService {
	
	@Autowired
	private BankRepository bankRepository;

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private IssuerResponseRepository responseRepository;
	
	public Bank findBank(String pan) {
		// TODO Auto-generated method stub
		String bin = pan.substring(0, 6);
		Bank bankIssuer = bankRepository.findByBin(bin);
		return bankIssuer;
	}
	
	public IssuerResponseDTO requestToIssuer(Bank issuerBank, InterbankRequestDTO request) {
		RestTemplate temp = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<InterbankRequestDTO> entity = new HttpEntity<>(request ,headers);
        
        System.out.print("lokacija: "+issuerBank.getUrl()+"pccRequest");
        IssuerResponseDTO dtoIR = temp.postForObject(issuerBank.getUrl()+"pccRequest",
                entity, IssuerResponseDTO.class);
        
        return dtoIR;
	}

	public void saveRequest(Request toSave) {
		this.requestRepository.save(toSave);
	}
	
	
	public IssuerResponse saveResponse(IssuerResponse toSave) {
		return this.responseRepository.save(toSave);
	}
	
}
	