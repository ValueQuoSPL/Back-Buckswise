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
@Table(name = "insurance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Insurance {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "userid")
	    private int userid;
	    
	    @Column(name = "name")
	    private String name;
	    
	    @Column(name = "insurancename")
	    private String insuranceName;
	    
	    @Column(name = "issuer")
	    private String issuer;
	    
	    @Column(name = "policymmode")
	    private String pMode;
	    
	    @Column(name = "policyname")
	    private String pName;
	    
	    @Column(name = "premiumname")
	    private String premiumName;
	    
	    @Column(name = "premium")
	    private String premium;
	    
	    @Column(name = "policyterm")
	    private String pterm;
	    
	    @Column(name = "sdate")
	    private String sDate;
	    
	    @Column(name = "sum")
	    private String sum;

	    @Column(name = "term")
		private String term;

		@Column(name = "policynumber")
		private String policynumber;

	    public Insurance() {
	    	
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

		public void setUserid(int userid2) {
			this.userid = userid2;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInsuranceName() {
			return insuranceName;
		}

		public void setInsuranceName(String insuranceName) {
			this.insuranceName = insuranceName;
		}

		public String getIssuer() {
			return issuer;
		}

		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}

		public String getpMode() {
			return pMode;
		}

		public void setpMode(String pMode) {
			this.pMode = pMode;
		}

		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
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

		public String getPterm() {
			return pterm;
		}

		public void setPterm(String pterm) {
			this.pterm = pterm;
		}

		public String getsDate() {
			return sDate;
		}

		public void setsDate(String sDate) {
			this.sDate = sDate;
		}

		public String getSum() {
			return sum;
		}

		public void setSum(String sum) {
			this.sum = sum;
		}

		public String getTerm() {
			return term;
		}

		public void setTerm(String term) {
			this.term = term;
		}

		public String getPolicynumber() {
			return policynumber;
		}

		public void setPolicynumber(String policynumber) {
			this.policynumber = policynumber;
		}


		public Insurance(int userid, String name, String insuranceName, String issuer, String pMode,
				String pName, String premiumName, String premium, String pterm, String sDate, String sum, String term, String policynumber) {
			super();
			this.userid = userid;
			this.name = name;
			this.insuranceName = insuranceName;
			this.issuer = issuer;
			this.pMode = pMode;
			this.pName = pName;
			this.premiumName = premiumName;
			this.premium = premium;
			this.pterm = pterm;
			this.sDate = sDate;
			this.sum = sum;
			this.term = term;
			this.policynumber = policynumber;
		}   
}
