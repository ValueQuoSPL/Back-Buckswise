package com.valuequo.buckswise.model;

public class MailVM {

	private String toMail;
	private String otp;

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public MailVM() {
		super();
	}

	@Override
	public String toString() {
		return "MailVM [toMail=" + toMail + ", otp=" + otp + "]";
	}

}
