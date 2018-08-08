package com.valuequo.buckswise.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
public class PaymentcheckMerchantTxnStatusVM {

	private String merchantTransactionId;
	private String paymentId;
	private String amount;

	public String getMerchantTransactionId() {
		return merchantTransactionId;
	}

	public void setMerchantTransactionId(String merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public PaymentcheckMerchantTxnStatusVM() {
		super();
	}

	@Override
	public String toString() {
		return "PaymentcheckMerchantTxnStatusVM [merchantTransactionId=" + merchantTransactionId + ", paymentId="
				+ paymentId + ", amount=" + amount + "]";
	}

}
