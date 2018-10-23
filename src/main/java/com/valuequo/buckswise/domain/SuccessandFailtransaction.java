package com.valuequo.buckswise.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "transactiondata")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SuccessandFailtransaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "mihpayid")
	private Long mihpayid;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "txnid")
	private String txnid;
	
	@Column(name = "productinfo")
	private String productinfo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "addedon")
	private String addedon;
	
	@Column(name = "userid")
	private Long userid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMihpayid() {
		return mihpayid;
	}

	public void setMihpayid(Long mihpayid) {
		this.mihpayid = mihpayid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnid() {
		return txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	public String getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(String productinfo) {
		this.productinfo = productinfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAddedon() {
		return addedon;
	}

	public void setAddedon(String addedon) {
		this.addedon = addedon;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public SuccessandFailtransaction(Long mihpayid, String status, String txnid, String productinfo,
			String email, String amount, String addedon, Long userid) {
		super();
		this.id = id;
		this.mihpayid = mihpayid;
		this.status = status;
		this.txnid = txnid;
		this.productinfo = productinfo;
		this.email = email;
		this.amount = amount;
		this.addedon = addedon;
		this.userid = userid;
	}

	public SuccessandFailtransaction() {
		super();
	}

	
}
