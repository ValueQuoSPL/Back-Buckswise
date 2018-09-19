package com.valuequo.buckswise.model;

import org.springframework.stereotype.Component;

@Component
public class Sucessful {
	
	private String split_info;
	private String customerName;
	private String additionalCharges;
	private String paymentMode;
	private String hash;
	private String status;
	private String paymentId;
	private String productInfo;
	private String customerEmail;
	private String customerPhone;
	private String merchantTransactionId;
	private String amount;
	private String udf2;
	private String notificationId;
	private String udf1;
	private String udf5;
	private String udf4;
	private String udf3;
	private String error_Message;
	public String getSplit_info() {
		return split_info;
	}
	public void setSplit_info(String split_info) {
		this.split_info = split_info;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAdditionalCharges() {
		return additionalCharges;
	}
	public void setAdditionalCharges(String additionalCharges) {
		this.additionalCharges = additionalCharges;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getMerchantTransactionId() {
		return merchantTransactionId;
	}
	public void setMerchantTransactionId(String merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUdf2() {
		return udf2;
	}
	public void setUdf2(String udf2) {
		this.udf2 = udf2;
	}
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public String getUdf1() {
		return udf1;
	}
	public void setUdf1(String udf1) {
		this.udf1 = udf1;
	}
	public String getUdf5() {
		return udf5;
	}
	public void setUdf5(String udf5) {
		this.udf5 = udf5;
	}
	public String getUdf4() {
		return udf4;
	}
	public void setUdf4(String udf4) {
		this.udf4 = udf4;
	}
	public String getUdf3() {
		return udf3;
	}
	public void setUdf3(String udf3) {
		this.udf3 = udf3;
	}
	public String getError_Message() {
		return error_Message;
	}
	public void setError_Message(String error_Message) {
		this.error_Message = error_Message;
	}
	public Sucessful() {
		super();
	}
	@Override
	public String toString() {
		return "Sucessful [split_info=" + split_info + ", customerName=" + customerName + ", additionalCharges="
				+ additionalCharges + ", paymentMode=" + paymentMode + ", hash=" + hash + ", status=" + status
				+ ", paymentId=" + paymentId + ", productInfo=" + productInfo + ", customerEmail=" + customerEmail
				+ ", customerPhone=" + customerPhone + ", merchantTransactionId=" + merchantTransactionId + ", amount="
				+ amount + ", udf2=" + udf2 + ", notificationId=" + notificationId + ", udf1=" + udf1 + ", udf5=" + udf5
				+ ", udf4=" + udf4 + ", udf3=" + udf3 + ", error_Message=" + error_Message + "]";
	}
	
}
