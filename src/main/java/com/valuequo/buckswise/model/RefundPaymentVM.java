package com.valuequo.buckswise.model;

import org.springframework.stereotype.Component;

@Component
public class RefundPaymentVM {

	private String _paymentId;
	private String _refundAmount;

	public String get_paymentId() {
		return _paymentId;
	}

	public void set_paymentId(String _paymentId) {
		this._paymentId = _paymentId;
	}

	public String get_refundAmount() {
		return _refundAmount;
	}

	public void set_refundAmount(String _refundAmount) {
		this._refundAmount = _refundAmount;
	}

	@Override
	public String toString() {
		return "RefundPaymentVM [_paymentId=" + _paymentId + ", _refundAmount=" + _refundAmount + "]";
	}

	public RefundPaymentVM() {
		super();
	}

}
