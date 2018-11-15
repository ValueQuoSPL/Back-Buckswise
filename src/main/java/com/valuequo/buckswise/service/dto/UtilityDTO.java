package com.valuequo.buckswise.service.dto;

public class UtilityDTO {
	
	private String name;
	private String value;
	private Long userid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public UtilityDTO(String name, String value, Long userid) {
		super();
		this.name = name;
		this.value = value;
		this.userid = userid;
	} 

}
