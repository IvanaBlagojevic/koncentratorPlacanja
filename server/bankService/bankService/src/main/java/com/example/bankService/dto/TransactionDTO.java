package com.example.bankService.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.bankService.domain.StatusOfPayment;

public class TransactionDTO {
	 
	    private String merchantOrderId;

	    private String acquirerOrderId;

	    private Date acquirerTimestamp;

	    private Long paymentId;

	    private StatusOfPayment status;

		public TransactionDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public String getMerchantOrderId() {
			return merchantOrderId;
		}

		public void setMerchantOrderId(String merchantOrderId) {
			this.merchantOrderId = merchantOrderId;
		}

		public String getAcquirerOrderId() {
			return acquirerOrderId;
		}

		public void setAcquirerOrderId(String acquirerOrderId) {
			this.acquirerOrderId = acquirerOrderId;
		}

		public Date getAcquirerTimestamp() {
			return acquirerTimestamp;
		}

		public void setAcquirerTimestamp(Date acquirerTimestamp) {
			this.acquirerTimestamp = acquirerTimestamp;
		}

		public Long getPaymentId() {
			return paymentId;
		}

		public void setPaymentId(Long paymentId) {
			this.paymentId = paymentId;
		}

		public StatusOfPayment getStatus() {
			return status;
		}

		public void setStatus(StatusOfPayment status) {
			this.status = status;
		}
	    
	    

}
