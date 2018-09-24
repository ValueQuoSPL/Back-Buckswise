package com.valuequo.buckswise.model;

import org.springframework.stereotype.Component;

@Component
public class RefundIDVM {

	private String refundId;

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public RefundIDVM() {
		super();
	}

	@Override
	public String toString() {
		return "RefundIDVM [refundId=" + refundId + "]";
	}

}
