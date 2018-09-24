package com.valuequo.buckswise.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.valuequo.buckswise.domain.Payment;

@Component
public class PaymentVM {
	/*
	 * "firstname":"nita","Amount":"1","Email":"nita@valuequo.com","phone":"2156537212","sucessUrl":"localhost:8080/successful.html"
	 * 
	 * */
	private String amount;
	private String firstName;
	private String eMail;
	private String phone;
	private String productInfo;
	private String sUrl;
	private String fUrl;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getfUrl() {
		return fUrl;
	}
	public void setfUrl(String fUrl) {	
		this.fUrl = fUrl;
	}
	
	@Override
	public String toString() {
		return "PaymentVM [amount=" + amount + ", firstName=" + firstName + ", eMail=" + eMail + ", phone=" + phone
				+ ", productInfo=" + productInfo + ", sUrl=" + sUrl + ", fUrl=" + fUrl + "]";
	}
	
	public PaymentVM(Payment payment) {
		this.amount = payment.getAmount();
		this.firstName = payment.getFirstName();
		this.eMail = payment.geteMail();
		this.phone = payment.getPhone();
		this.productInfo = payment.getProductInfo();
		this.sUrl = payment.getsUrl();
		this.fUrl = payment.getfUrl();
	}
	public PaymentVM() {
		super();
	}
			
}