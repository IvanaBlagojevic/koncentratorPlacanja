package com.example.bankAcquirer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.bankAcquirer.domain.Account;
import com.example.bankAcquirer.domain.BuyerInfo;
import com.example.bankAcquirer.domain.Card;
import com.example.bankAcquirer.domain.Merchant;
import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.domain.Transaction;
import com.example.bankAcquirer.dto.InterbankRequestDTO;
import com.example.bankAcquirer.dto.InterbankResponseDTO;
import com.example.bankAcquirer.repository.AccountRepository;
import com.example.bankAcquirer.repository.BuyerInfoRepository;
import com.example.bankAcquirer.repository.CardRepository;
import com.example.bankAcquirer.repository.MerchantRepository;
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
	
	private String addressPCC = "http://localhost:8091/";
	
	
	public BuyerInfo addNewBuyerInfo(BuyerInfo buyerInfo) throws NotFoundException {
		if (buyerInfo.getPayment() == null) {
			throw new NotFoundException("Invalid payment!");
		}
		
		Merchant merchant = this.merchantRepository.findByMerchantId(buyerInfo.getPayment().getMerchantId());
		List<Account> accountsOfThisBank = this.accountRepository.findAll();
		
		boolean isThisBank = false;
		
		for(int i =0; i < accountsOfThisBank.size(); i++) {
			if(accountsOfThisBank.get(i).getAccountNumber().equals(buyerInfo.getPan())) {
				System.out.println("jednaki racuni postojecem");
				Transaction request = createTransaction(buyerInfo, merchant,accountsOfThisBank.get(i));
				isThisBank = true;
			}
		}
		if(isThisBank == false) {
			Transaction request = createTransactionForPCC(buyerInfo, merchant);
			InterbankResponseDTO response = requestToPCC(request,buyerInfo);
			request.setAccountAccepter(response.getAccountNumber());
			request.setStatus(response.getStatus());
			this.transactionRepository.save(request);	
		}
		return buyerInfo;
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
		irDTO.setAcquirerOrderId(request.getId().toString());
		irDTO.setAcquirerTimestamp(request.getTimestamp());
		irDTO.setAmount(request.getAmount());
		irDTO.setCardHolderName(buyerInfo.getCardHolderName());
		irDTO.setDateTillExpired(buyerInfo.getDateTillExpired());
		irDTO.setPan(buyerInfo.getPan());
		irDTO.setPaymentId(request.getPayment().getId().toString());
		irDTO.setSecurityCode(buyerInfo.getSecurityCode());
		
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<InterbankRequestDTO> entity = new HttpEntity<>(irDTO ,headers);
		
		InterbankResponseDTO dtoIA = temp.postForObject(addressPCC+"/pcc", entity, InterbankResponseDTO.class);
		System.out.print("vratio "+ dtoIA.getAccountNumber()+" "+dtoIA.getStatus());
		return dtoIA;
	}
	
	private Transaction createTransaction(BuyerInfo buyerInfo, Merchant merchant, Account account) {
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
		return entity;
	}
	
	
	
	
}
