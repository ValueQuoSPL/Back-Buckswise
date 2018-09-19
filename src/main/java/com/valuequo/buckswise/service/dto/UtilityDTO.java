package com.valuequo.buckswise.service.dto;

public class UtilityDTO {
	
	private String name;
	private String value;
	private int userid;
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public UtilityDTO(String name, String value, int userid) {
		super();
		this.name = name;
		this.value = value;
		this.userid = userid;
	} 

}
