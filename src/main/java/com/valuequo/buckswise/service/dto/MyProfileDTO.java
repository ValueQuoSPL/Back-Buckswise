package com.valuequo.buckswise.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class MyProfileDTO implements Serializable {

    /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
//
	private Long id;
    
    private Long uid;

	private String firstName;

    private String middleName;

    private String lastName;

    private LocalDate dob;

    private String gender;

    private String maritalStatus;

    private String mobileNumber;

    private String alternateNumber;

    private String occupation;

    private String company;

    private String howDidYouKnow;

    private String address;

    private String emailId;

    private String pan;

    private String country;

    private String state;

    private String city;

    private String pin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   

    public String getFirstName() {
        return firstName;
    }

    public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHowDidYouKnow() {
        return howDidYouKnow;
    }

    public void setHowDidYouKnow(String howDidYouKnow) {
        this.howDidYouKnow = howDidYouKnow;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "FamillyDTO{" +
//            "UID=" + getUID() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", dob='" + getDob() + "'" +
            ", gender='" + getGender() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", alternateNumber='" + getAlternateNumber() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", company='" + getCompany() + "'" +
            ", howDidYouKnow='" + getHowDidYouKnow() + "'" +
            ", address='" + getAddress() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", pan='" + getPan() + "'" +
            ", country='" + getCountry() + "'" +
            ", state='" + getState() + "'" +
            ", city='" + getCity() + "'" +
            ", pin='" + getPin() + "'" +
            "}";
    }
}
