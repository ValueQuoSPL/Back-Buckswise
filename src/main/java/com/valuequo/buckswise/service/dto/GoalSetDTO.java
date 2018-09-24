package com.valuequo.buckswise.service.dto;

import java.sql.Date;

public class GoalSetDTO {
	
	private long id;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private Long UID;

	private String goaltype;

    private String goalname;

    private String priority;

    private String yeartogoal;

    private String presentcost;

    private String notes;
    
    private String futurecost;
    
    private String requiremonthinvest;
    
    private String fundshortage;
    
    private Date dateofcreation;
    
    private String linkassets;
    
    private String assetname;
    
    private String value;
    
    private String valuetomap;

	public Long getUID() {
		return UID;
	}

	public void setUID(Long uID) {
		UID = uID;
	}

	public String getGoaltype() {
		return goaltype;
	}

	public void setGoaltype(String goaltype) {
		this.goaltype = goaltype;
	}

	public String getGoalname() {
		return goalname;
	}

	public void setGoalname(String goalname) {
		this.goalname = goalname;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getYeartogoal() {
		return yeartogoal;
	}

	public String getAssetname() {
		return assetname;
	}

	public void setAssetname(String assetname) {
		this.assetname = assetname;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValuetomap() {
		return valuetomap;
	}

	public void setValuetomap(String valuetomap) {
		this.valuetomap = valuetomap;
	}

	public void setYeartogoal(String yeartogoal) {
		this.yeartogoal = yeartogoal;
	}

	public String getPresentcost() {
		return presentcost;
	}

	public void setPresentcost(String presentcost) {
		this.presentcost = presentcost;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFuturecost() {
		return futurecost;
	}

	public void setFuturecost(String futurecost) {
		this.futurecost = futurecost;
	}

	public String getRequiremonthinvest() {
		return requiremonthinvest;
	}

	public void setRequiremonthinvest(String requiremonthinvest) {
		this.requiremonthinvest = requiremonthinvest;
	}

	public String getFundshortage() {
		return fundshortage;
	}

	public void setFundshortage(String fundshortage) {
		this.fundshortage = fundshortage;
	}
	
	public Date getDateofcreation() {
		return dateofcreation;
	}

	public void setDateofcreation(Date dateofcreation) {
		this.dateofcreation = dateofcreation;
	}
	public String getLinkassets() {
		return linkassets;
	}

	public void setLinkassets(String linkassets) {
		this.linkassets = linkassets;
	}

	@Override
	public String toString() {
		return "GoalSetDTO [UID=" + UID + ", goaltype=" + goaltype + ", goalname=" + goalname + ", priority=" + priority
				+ ", yeartogoal=" + yeartogoal + ", presentcost=" + presentcost + ", notes=" + notes + ", futurecost="
				+ futurecost + ", requiremonthinvest=" + requiremonthinvest + ", fundshortage=" + fundshortage
				+ ", dateofcreation=" + dateofcreation + ", linkassets=" + linkassets + ", assetname=" + assetname
				+ ", value=" + value + ", valuetomap=" + valuetomap + "]";
	}
	
	

}
