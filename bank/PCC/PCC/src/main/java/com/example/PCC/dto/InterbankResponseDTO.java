package com.example.PCC.dto;

public class InterbankResponseDTO {
	
	private String accountNumber;//broj od issuera
	 
	private String status;
	 
	 

	public InterbankResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public InterbankResponseDTO(String accountNumber, String status) {
		super();
		this.accountNumber = accountNumber;
		this.status = status;
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
