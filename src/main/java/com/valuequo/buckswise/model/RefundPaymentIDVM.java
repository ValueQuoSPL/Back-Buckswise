package com.valuequo.buckswise.model;

import org.springframework.stereotype.Component;

@Component
public class RefundPaymentIDVM {

	private String paymentID;

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public RefundPaymentIDVM() {
		super();
	}

	@Override
	public String toString() {
		return "RefundPaymentIDVM [paymentID=" + paymentID + "]";
	}

}
