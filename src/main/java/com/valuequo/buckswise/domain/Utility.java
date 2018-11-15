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
@Table(name = "utility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Utility {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "userid")
    private Long userid;
    
    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private String amount;
  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Utility() {
		
	}
	
	public Utility(String name, String amount, Long userid) {
		super();
		this.name = name;
		this.amount = amount;
		this.userid = userid;
	}

    
}
