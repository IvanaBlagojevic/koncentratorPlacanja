package com.example.bankAcquirer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.bankAcquirer.domain.Account;
import com.example.bankAcquirer.domain.BuyerInfo;
import com.example.bankAcquirer.domain.Card;
import com.example.bankAcquirer.domain.IssuerResponse;
import com.example.bankAcquirer.domain.Merchant;
import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.domain.ResponseToKP;
import com.example.bankAcquirer.domain.Transaction;
import com.example.bankAcquirer.dto.InterbankRequestDTO;
import com.example.bankAcquirer.dto.InterbankResponseDTO;
import com.example.bankAcquirer.repository.AccountRepository;
import com.example.bankAcquirer.repository.BuyerInfoRepository;
import com.example.bankAcquirer.repository.CardRepository;
import com.example.bankAcquirer.repository.IssuerResponseRepository;
import com.example.bankAcquirer.repository.MerchantRepository;
import com.example.bankAcquirer.repository.ResponseToKPRepository;
import com.example.bankAcquirer.repository.TransactionRepository;

import javassist.NotFoundException;


@Service
public class BuyerInfoService {
	

	@Autowired
	private BuyerInfoRepository buyerInfoRepository;

	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ResponseToKPRepository responseToKPRepository;
	
	@Autowired
	private IssuerResponseRepository issuerRepository;
	
	private String addressPCC = "http://localhost:8091/";
	
	
	public ResponseToKP addNewBuyerInfo(BuyerInfo buyerInfo) throws NotFoundException {
		if (buyerInfo.getPayment() == null) {
			throw new NotFoundException("Invalid payment!");
		}
		
		Merchant merchant = this.merchantRepository.findByMerchantId(buyerInfo.getPayment().getMerchantId());
		List<Account> accountsOfThisBank = this.accountRepository.findAll();
		
		boolean thisBank = false;
		
		for(int i =0; i < accountsOfThisBank.size(); i++) {
			if(accountsOfThisBank.get(i).getAccountNumber().equals(buyerInfo.getPan())) {
				System.out.println("jednaki racuni postojecem");
				ResponseToKP request = createTransaction(buyerInfo, merchant,accountsOfThisBank.get(i));
				thisBank = true;
				return request;
			}
		}
		if(isThisBank(buyerInfo.getPan()) && thisBank==false) {
			System.out.println("Racun ne postoji u datoj banci");
			ResponseToKP request = createTransaction(buyerInfo, merchant,buyerInfo.getPan());
			thisBank = true;
			return request;
		}
		if(thisBank == false) {
			Transaction request = createTransactionForPCC(buyerInfo, merchant);
			InterbankResponseDTO response = requestToPCC(request,buyerInfo);
			if(response !=null) {
				request.setAccountAccepter(response.getAccountNumber());
				request.setStatus(response.getStatus());
				this.transactionRepository.save(request);	
				ResponseToKP resToKP= new ResponseToKP();
				resToKP.setAcquirerOrderId(response.getAcquirerOrderId());
				resToKP.setAcquirerTimestamp(response.getAcquirerTimestamp());
				resToKP.setPaymentId(request.getPayment().getId());
				resToKP.setStatus(response.getStatus());
				resToKP.setMerchantOrderId(request.getId());//nisam siguran
				this.responseToKPRepository.save(resToKP);
				
				IssuerResponse toSave = new IssuerResponse();
				toSave.setAccount(response.getAccountNumber());
				toSave.setAcquirerOrderId(response.getAcquirerOrderId());
				toSave.setAcquirerTimestamp(response.getAcquirerTimestamp());
				toSave.setIssuerOrderId(response.getIssuerOrderId());
				toSave.setIssuerTimestamp(response.getIssuerTimestamp());
				toSave.setTransactionStatus(response.getStatus());
				this.issuerRepository.save(toSave);
				
				return resToKP;
			}else {
				System.out.println("PCC nije pronasao banku na osnovu PAN-a");
				ResponseToKP request1 = createTransaction(buyerInfo, merchant,buyerInfo.getPan());
				return request1;
			}
		}
		
		return null;
	}
	
	private ResponseToKP createTransaction(BuyerInfo buyerInfo, Merchant merchant, String pan) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		//transaction.setAccountAccepter(pan);
		transaction.setAccountPayer(pan);
		transaction.setPayment(buyerInfo.getPayment());
		transaction.setTimestamp(new Date());
		transaction.setAmount(-buyerInfo.getPayment().getAmount());
		transaction.setStatus("error");
				
		
		Transaction entity = this.transactionRepository.save(transaction);
		ResponseToKP resToKP= new ResponseToKP();
		resToKP.setAcquirerOrderId(transaction.getId());
		resToKP.setAcquirerTimestamp(new Date());
		resToKP.setPaymentId(transaction.getPayment().getId());
		resToKP.setStatus(transaction.getStatus());
		resToKP.setMerchantOrderId(transaction.getId());//nisam siguran
		this.responseToKPRepository.save(resToKP);
		
		IssuerResponse toSave = new IssuerResponse();
		toSave.setAccount(pan);
		toSave.setAcquirerOrderId(transaction.getId());
		toSave.setAcquirerTimestamp(resToKP.getAcquirerTimestamp());
		toSave.setTransactionStatus(transaction.getStatus());
		this.issuerRepository.save(toSave);
		
		return resToKP;
	}

	private Transaction createTransactionForPCC(BuyerInfo buyerInfo, Merchant merchant) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		//transaction.setAccountAccepter(account.getAccountNumber());
		transaction.setAccountPayer(buyerInfo.getPan());
		transaction.setPayment(buyerInfo.getPayment());
		transaction.setTimestamp(new Date());
		transaction.setAmount(-buyerInfo.getPayment().getAmount());
		
		List<Transaction> allTransactions = this.transactionRepository.findAll();
		transaction.setId(Long.valueOf(allTransactions.size()+1));
		return transaction;
	}
	
	
	private InterbankResponseDTO requestToPCC(Transaction request,BuyerInfo buyerInfo) {
		// TODO Auto-generated method stub
		InterbankRequestDTO irDTO = new InterbankRequestDTO();
		irDTO.setAcquirerAccount(request.getAccountPayer());
		irDTO.setAcquirerOrderId(request.getId());
		irDTO.setAcquirerTimestamp(request.getTimestamp());
		irDTO.setAmount(request.getAmount());
		irDTO.setCardHolderName(buyerInfo.getCardHolderName());
		irDTO.setDateTillExpired(buyerInfo.getDateTillExpired());
		irDTO.setPan(buyerInfo.getPan());
		irDTO.setPaymentId(request.getPayment().getId());
		irDTO.setSecurityCode(buyerInfo.getSecurityCode());
		
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<InterbankRequestDTO> entity = new HttpEntity<>(irDTO ,headers);

		try {
			InterbankResponseDTO dtoIR = temp.postForObject(addressPCC+"/pcc", entity, InterbankResponseDTO.class);
			System.out.print("vratio "+ dtoIR.getAccountNumber()+" "+dtoIR.getStatus());
			return dtoIR;
		} catch (HttpClientErrorException ex)   {
		    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
		        return null;
		    }
		}
		
		
		
		/*
		 * this.merchantOrderId = merchantOrderId;
		this.acquirerOrderId = acquirerOrderId;
		this.acquirerTimestamp = acquirerTimestamp;
		this.paymentId = paymentId;
		this.status = status;*/
		
		return null;
	}
	
	private ResponseToKP createTransaction(BuyerInfo buyerInfo, Merchant merchant, Account account) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		transaction.setAccountAccepter(account.getAccountNumber());
		transaction.setAccountPayer(account.getAccountNumber());
		transaction.setPayment(buyerInfo.getPayment());
		transaction.setTimestamp(new Date());
		transaction.setAmount(-buyerInfo.getPayment().getAmount());
		//transaction.setStatus("success");
		
		if(account.getAmount()>buyerInfo.getPayment().getAmount()) {
			Card card = cardRepository.findByPan(buyerInfo.getPan());
			if(card != null) {
				System.out.println(card.getExpiryDate());
				System.out.println(buyerInfo.getDateTillExpired());
				//Date myDate = new Date();
				//System.out.println(myDate);
				String cardExpiredDate =new SimpleDateFormat("yyyy-MM-dd").format(card.getExpiryDate());
				String buyerInfoDate=new SimpleDateFormat("yyyy-MM-dd").format(buyerInfo.getDateTillExpired());
				if(card.getSecureCode().equals(buyerInfo.getSecurityCode())
						&& cardExpiredDate.equals(buyerInfoDate)){
					transaction.setStatus("success");
					//na kraju oduzeti novac sa racuna
					account.setAmount(account.getAmount()-buyerInfo.getPayment().getAmount());
					this.accountRepository.save(account);
				}else {
					transaction.setStatus("error");
				}
				
			}else {
				transaction.setStatus("failed");
			}
		}else {
			transaction.setStatus("failed");
		}
		
		Transaction entity = this.transactionRepository.save(transaction);
		ResponseToKP resToKP= new ResponseToKP();
		resToKP.setAcquirerOrderId(transaction.getId());
		resToKP.setAcquirerTimestamp(new Date());
		resToKP.setPaymentId(transaction.getPayment().getId());
		resToKP.setStatus(transaction.getStatus());
		resToKP.setMerchantOrderId(transaction.getId());//nisam siguran
		this.responseToKPRepository.save(resToKP);
		
		IssuerResponse toSave = new IssuerResponse();
		toSave.setAccount(account.getAccountNumber());
		toSave.setAcquirerOrderId(transaction.getId());
		toSave.setAcquirerTimestamp(resToKP.getAcquirerTimestamp());
		toSave.setTransactionStatus(transaction.getStatus());
		this.issuerRepository.save(toSave);
		
		return resToKP;
	}
	

	public ResponseToKP saveResponseToKP(ResponseToKP response) 
	{
		
		return responseToKPRepository.save(response);
	}

	public Boolean isThisBank(String pan) {
		// TODO Auto-generated method stub
		String bin = pan.substring(0, 6);
		if(bin.equals("111111")) {//zakucano
			return true;
		}
		
		return false;
	}
	
}
