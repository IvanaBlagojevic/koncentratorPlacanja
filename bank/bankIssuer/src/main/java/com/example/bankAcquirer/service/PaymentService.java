package com.example.bankAcquirer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankAcquirer.domain.Account;
import com.example.bankAcquirer.domain.Card;
import com.example.bankAcquirer.domain.Merchant;
import com.example.bankAcquirer.domain.Payment;
import com.example.bankAcquirer.domain.Transaction;
import com.example.bankAcquirer.dto.InterbankRequestDTO;
import com.example.bankAcquirer.dto.PaymentDTO;
import com.example.bankAcquirer.repository.AccountRepository;
import com.example.bankAcquirer.repository.CardRepository;
import com.example.bankAcquirer.repository.MerchantRepository;
import com.example.bankAcquirer.repository.PaymentRepository;
import com.example.bankAcquirer.repository.TransactionRepository;

import javassist.NotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	private String frontUrl ="http://localhost:4201/";
	
	
	public Payment addNewPayment(Payment payment) 
	{
		
		return paymentRepository.save(payment);
	}
	
	public Optional<Payment> findById(Long id) 
	{
		
		return paymentRepository.findById(id);
	}

	public Payment generatePayment(PaymentDTO payment) throws NotFoundException {
		// TODO Auto-generated method stub
		Merchant merchant = this.merchantRepository.findByMerchantId(payment.getMerchantId());
		if (merchant == null) {
			throw new NotFoundException("Invalid merchant id!");
		}
		if (!merchant.getMerchantPassword().equals(payment.getMerchantPassword())) {
			throw new NotFoundException("Invalid merchant password!");
		}
		
		Payment entity = new Payment(payment);
		List<Payment> listofAll=paymentRepository.findAll();
		entity.setPaymentId( Long.valueOf(listofAll.size()+1));
		entity.setPaymentUrl(this.frontUrl + "payment/" + entity.getPaymentId());
		Payment savedEntity= this.paymentRepository.save(entity);
		
		this.paymentRepository.save(savedEntity);
			
		return savedEntity;
	}

	public Optional<Payment> findByPaymentId(String id) {
		// TODO Auto-generated method stub
		return this.paymentRepository.findByPaymentId(id);
	}

	public Transaction generatePayment(InterbankRequestDTO dto) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		//transaction.setAccountAccepter(dto.getAcquirerAccount());
		transaction.setAccountPayer(dto.getPan());
		transaction.setTimestamp(new Date());
		transaction.setAmount(-dto.getAmount());
		transactionRepository.save(transaction);
		
		Account account = this.accountRepository.findByAccountNumber(dto.getPan());
		if(account != null) {
			if(account.getAmount()>dto.getAmount()) {
				System.out.print(dto.getPan());
				Card card = cardRepository.findByPan(dto.getPan());
				if(card != null) {
					System.out.println(card.getExpiryDate());
					System.out.println(dto.getDateTillExpired());
					//Date myDate = new Date();
					System.out.println(card.getSecureCode());
					System.out.println(dto.getSecurityCode());
					String cardExpiredDate =new SimpleDateFormat("yyyy-MM-dd").format(card.getExpiryDate());
					String buyerInfoDate=new SimpleDateFormat("yyyy-MM-dd").format(dto.getDateTillExpired());
					if(card.getSecureCode().equals(dto.getSecurityCode())
							&& cardExpiredDate.equals(buyerInfoDate)){
						transaction.setStatus("success");
						//na kraju oduzeti novac sa racuna
						account.setAmount(account.getAmount()+dto.getAmount());//posto je amount negativna vrednost
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
		}else {
			transaction.setStatus("failed");
		}
		
		Transaction entity = this.transactionRepository.save(transaction);
		
		return entity;
	}
	

}
