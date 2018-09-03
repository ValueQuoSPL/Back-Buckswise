package com.valuequo.buckswise.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "credit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Credit {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "userid")
    private int userid;
    
    @Column(name = "bank")
    private String bank;
    
    @Column(name = "balance")
    private String balance;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "roi")
    private String roi;
  
    @Column(name = "creditlimit")
    private String lt;
    
    @Column(name = "creditpay")
    private String pay;
    
    @Column(name = "creditusage")
    private String usage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Credit() {
		
	}

	public Credit(int userid, String bank, String balance, String type, String roi, String lt, String pay, String usage) {
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
