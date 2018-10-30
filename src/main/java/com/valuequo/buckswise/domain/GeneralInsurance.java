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
@Table(name = "generalinsurance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GeneralInsurance {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "userid")
    private int userid;
    
    @Column(name = "insurename")
    private String insureName;
    
    @Column(name = "policyname")
    private String policyName;
    
    @Column(name = "issuer")
    private String issuer;
    
    @Column(name = "policdate")
    private String policyDate;
    
    @Column(name = "policynumber")
    private String policyNumber;
    
    @Column(name = "premiumname")
    private String premiumName;
    
    @Column(name = "premium")
    private String premium;
    
    @Column(name = "premiumterm")
    private String premiumTerm;
    
    @Column(name = "sum")
    private String sum;
    
    @Column(name = "proposer")
    private String proposer;

    public GeneralInsurance() {
    	
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

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getPolicyDate() {
		return policyDate;
	}

	public void setPolicyDate(String policyDate) {
		this.policyDate = policyDate;
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

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	
	public String getProposer() {
		return proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public GeneralInsurance(int userid, String insureName, String policyName, String issuer, String policyDate,
			String policyNumber, String premiumName, String premium, String premiumTerm, String sum, String proposer) {
		super();
		this.userid = userid;
		this.insureName = insureName;
		this.policyName = policyName;
		this.issuer = issuer;
		this.policyDate = policyDate;
		this.policyNumber = policyNumber;
		this.premiumName = premiumName;
		this.premium = premium;
		this.premiumTerm = premiumTerm;
		this.sum = sum;
		this.proposer = proposer;
	}
    

}
