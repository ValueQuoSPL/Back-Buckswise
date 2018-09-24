package com.valuequo.buckswise.service.dto;

public class CreditDTO {
	
	private int userid;
	private String bank;
	private String balance;
	private String type;
	private String roi;
	private String lt;
	private String pay;
	private String usage;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoi() {
		return roi;
	}
	public void setRoi(String roi) {
		this.roi = roi;
	}
	public String getLt() {
		return lt;
	}
	public void setLt(String lt) {
		this.lt = lt;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public CreditDTO(int userid, String bank, String balance, String type, String roi, String lt, String pay,
			String usage) {
		super();
		this.userid = userid;
		this.bank = bank;
		this.balance = balance;
		this.type = type;
		this.roi = roi;
		this.lt = lt;
		this.pay = pay;
		this.usage = usage;
	}
	
	
}
