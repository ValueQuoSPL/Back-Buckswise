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
@Table(name = "medinsratecard")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MedInsRateCard implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "hospitaltype")
    private String hospitaltype;
    
    @Column(name = "roomtype")
    private String roomtype;
    
    @Column(name = "price")
    private String price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHospitaltype() {
		return hospitaltype;
	}

	public void setHostpitaltype(String hostpitaltype) {
		this.hospitaltype = hostpitaltype;
	}



	public String getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public MedInsRateCard() {
		super();
	}

	public MedInsRateCard(Long id, String hospitaltype, String roomtype, String price) {
		super();
		this.id = id;
		this.hospitaltype = hospitaltype;
		this.roomtype = roomtype;
		this.price = price;
	}


}
