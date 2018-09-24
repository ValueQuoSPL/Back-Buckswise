package com.valuequo.buckswise.model;

import org.springframework.stereotype.Component;

@Component
public class GetPaymentResponse {
	
	private String merchantTxnId;

	public String getMerchantTxnId() {
		return merchantTxnId;
	}

	public void setMerchantTxnId(String merchantTxnId) {
		this.merchantTxnId = merchantTxnId;
	}

	@Override
	public String toString() {
		return "GetPaymentResponse [merchantTxnId=" + merchantTxnId + "]";
	}
	
	
}
