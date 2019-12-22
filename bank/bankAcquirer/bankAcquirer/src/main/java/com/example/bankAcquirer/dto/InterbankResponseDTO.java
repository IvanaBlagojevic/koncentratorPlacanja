package com.example.bankAcquirer.dto;

public class InterbankResponseDTO {
	
	private String accountNumber;
	 
	private String status;
	 
	 

	public InterbankResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	    
	 
}
