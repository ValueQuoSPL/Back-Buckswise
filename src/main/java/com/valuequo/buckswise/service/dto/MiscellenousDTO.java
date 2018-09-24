package com.valuequo.buckswise.service.dto;

public class MiscellenousDTO {
	
	private int userid;
	private String name;
	private String value;
	

	public MiscellenousDTO(int userid, String name, String value) {
		super();
		this.userid = userid;
		this.name = name;
		this.value = value;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
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
	
	

}
