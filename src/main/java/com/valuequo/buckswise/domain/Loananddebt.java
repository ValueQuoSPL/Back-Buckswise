package com.valuequo.buckswise.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "loananddebt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loananddebt {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "appname")
	private String appName;
	
	@Column(name = "checktype")
	private String checkType;
	
	@Column(name = "itype")
	private String itype;
	
	@Column(name = "ldate")
	private String ldate;
	
	@Column(name = "lendername")
	private String lenderName;
	
	@Column(name = "ltype")
	private String ltype;
	
	@Column(name = "rdate")
	private String rdate;
	
	@Column(name = "roi")
	private String roi;
	
	@Column(name = "tenure")
	private String tenure;

	@Column(name = "outstandingprincipal")
	private String Outstandingpricipal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getItype() {
		return itype;
	}

	public void setItype(String itype) {
		this.itype = itype;
	}

	public String getLdate() {
		return ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getLenderName() {
		return lenderName;
	}

	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}

	public String getLtype() {
		return ltype;
	}

	public void setLtype(String ltype) {
		this.ltype = ltype;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public String getRoi() {
		return roi;
	}

	public void setRoi(String roi) {
		this.roi = roi;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getOutstandingpricipal() {
		return Outstandingpricipal;
	}

	public void setOutstandingpricipal(String outstandingpricipal) {
		Outstandingpricipal = outstandingpricipal;
	}

	public Loananddebt() {
		
	}
	
	public Loananddebt(int userid, String amount, String appName, String checkType, String itype,
			String ldate, String lenderName, String ltype, String rdate, String roi, String tenure, String Outstandingprincipal) {
		super();
		this.id = id;
		this.userid = userid;
		this.amount = amount;
		this.appName = appName;
		this.checkType = checkType;
		this.itype = itype;
		this.ldate = ldate;
		this.lenderName = lenderName;
		this.ltype = ltype;
		this.rdate = rdate;
		this.roi = roi;
		this.tenure = tenure;
	}
}
