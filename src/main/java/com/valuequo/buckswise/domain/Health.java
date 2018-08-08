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
@Table(name = "health")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Health {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "userid")
    private int userid;
    
    @Column(name = "insurename")
    private String insureName;
    
    @Column(name = "issuer")
    private String issuer;
    
    @Column(name = "policymode")
    private String policyMode;
    
    @Column(name = "policyname")
    private String policyName;
    
    @Column(name = "policynumber")
    private String policyNumber;
    
    @Column(name = "premiumname")
    private String premiumName;
    
    @Column(name = "premium")
    private String premium;
    
    
    @Column(name = "premiumterm")
    private String premiumTerm;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "sum")
    private String sum;

    public Health() {
    	
    } 
    
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

	public String getInsureName() {
		return insureName;
	}

	public void setInsureName(String insureName) {
		this.insureName = insureName;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getPolicyMode() {
		return policyMode;
	}

	public void setPolicyMode(String policyMode) {
		this.policyMode = policyMode;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPremiumName() {
		return premiumName;
	}

	public void setPremiumName(String premiumName) {
		this.premiumName = premiumName;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getPremiumTerm() {
		return premiumTerm;
	}

	public void setPremiumTerm(String premiumTerm) {
		this.premiumTerm = premiumTerm;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public Health(int userid, String insureName, String issuer, String policyMode, String policyName,
			String policyNumber, String premiumName, String premium, String premiumTerm, String date, String sum) {
		super();
		this.userid = userid;
		this.insureName = insureName;
		this.issuer = issuer;
		this.policyMode = policyMode;
		this.policyName = policyName;
		this.policyNumber = policyNumber;
		this.premiumName = premiumName;
		this.premium = premium;
		this.premiumTerm = premiumTerm;
		this.date = date;
		this.sum = sum;
	}
    
    
    
}
