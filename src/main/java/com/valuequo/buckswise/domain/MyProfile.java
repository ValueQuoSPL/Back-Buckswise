	package com.valuequo.buckswise.domain;

	import org.hibernate.annotations.Cache;
	import org.hibernate.annotations.CacheConcurrencyStrategy;
	import org.springframework.stereotype.Component;

	import javax.persistence.*;

	import java.io.Serializable;
	import java.time.LocalDate;
	import java.util.Objects;

	@Entity
	@Table(name = "myprofilee")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public class MyProfile implements Serializable {

	    private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "uid")
	    private Long uid;

	    @Column(name = "first_name")
	    private String firstName;

	    @Column(name = "middle_name")
	    private String middleName;

	    @Column(name = "last_name")
	    private String lastName;

	    @Column(name = "date_of_birth")
	    private LocalDate dob;

	    @Column(name = "gender")
	    private String gender;

	    @Column(name = "marital_status")
	    private String maritalStatus;

	    @Column(name = "mobile_number")
	    private String mobileNumber;

	    @Column(name = "alternate_number")
	    private String alternateNumber;

	    @Column(name = "occupation")
	    private String occupation;

	    @Column(name = "company_name")
	    private String company;

	    @Column(name = "how_did_you_know")
	    private String howDidYouKnow;

	    @Column(name = "address")
	    private String address;

	    @Column(name = "email_id")
	    private String emailId;

	    @Column(name = "pan_number")
	    private String pan;

	    @Column(name = "country_name")
	    private String country;

	    @Column(name = "state_name")
	    private String state;

	    @Column(name = "city_name")
	    private String city;

	    @Column(name = "pin_number")
	    private String pin;

	    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
	    
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }

	   
	    public Long getUid() {
			return uid;
		}
	    
		public void setUid(Long uid) {
			this.uid = uid;
		}
		
		public String getFirstName() {
	        return firstName;
	    }

	    public MyProfile firstName(String firstName) {
	        this.firstName = firstName;
	        return this;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getMiddleName() {
	        return middleName;
	    }

	    public MyProfile middleName(String middleName) {
	        this.middleName = middleName;
	        return this;
	    }

	    public void setMiddleName(String middleName) {
	        this.middleName = middleName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public MyProfile lastName(String lastName) {
	        this.lastName = lastName;
	        return this;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public LocalDate getDob() {
	        return dob;
	    }

	    public MyProfile dob(LocalDate dob) {
	        this.dob = dob;
	        return this;
	    }

	    public void setDob(LocalDate dob) {
	        this.dob = dob;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public MyProfile gender(String gender) {
	        this.gender = gender;
	        return this;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getMaritalStatus() {
	        return maritalStatus;
	    }

	    public MyProfile maritalStatus(String maritalStatus) {
	        this.maritalStatus = maritalStatus;
	        return this;
	    }

	    public void setMaritalStatus(String maritalStatus) {
	        this.maritalStatus = maritalStatus;
	    }

	    public String getMobileNumber() {
	        return mobileNumber;
	    }

	    public MyProfile mobileNumber(String mobileNumber) {
	        this.mobileNumber = mobileNumber;
	        return this;
	    }

	    public void setMobileNumber(String mobileNumber) {
	        this.mobileNumber = mobileNumber;
	    }

	    public String getAlternateNumber() {
	        return alternateNumber;
	    }

	    public MyProfile alternateNumber(String alternateNumber) {
	        this.alternateNumber = alternateNumber;
	        return this;
	    }

	    public void setAlternateNumber(String alternateNumber) {
	        this.alternateNumber = alternateNumber;
	    }

	    public String getOccupation() {
	        return occupation;
	    }

	    public MyProfile occupation(String occupation) {
	        this.occupation = occupation;
	        return this;
	    }

	    public void setOccupation(String occupation) {
	        this.occupation = occupation;
	    }

	    public String getCompany() {
	        return company;
	    }

	    public MyProfile company(String company) {
	        this.company = company;
	        return this;
	    }

	    public void setCompany(String company) {
	        this.company = company;
	    }

	    public String getHowDidYouKnow() {
	        return howDidYouKnow;
	    }

	    public MyProfile howDidYouKnow(String howDidYouKnow) {
	        this.howDidYouKnow = howDidYouKnow;
	        return this;
	    }

	    public void setHowDidYouKnow(String howDidYouKnow) {
	        this.howDidYouKnow = howDidYouKnow;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public MyProfile address(String address) {
	        this.address = address;
	        return this;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getEmailId() {
	        return emailId;
	    }

	    public MyProfile emailId(String emailId) {
	        this.emailId = emailId;
	        return this;
	    }

	    public void setEmailId(String emailId) {
	        this.emailId = emailId;
	    }

	    public String getPan() {
	        return pan;
	    }

	    public MyProfile pan(String pan) {
	        this.pan = pan;
	        return this;
	    }

	    public void setPan(String pan) {
	        this.pan = pan;
	    }

	    public String getCountry() {
	        return country;
	    }

	    public MyProfile country(String country) {
	        this.country = country;
	        return this;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }

	    public String getState() {
	        return state;
	    }

	    public MyProfile state(String state) {
	        this.state = state;
	        return this;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public String getCity() {
	        return city;
	    }

	    public MyProfile city(String city) {
	        this.city = city;
	        return this;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getPin() {
	        return pin;
	    }

	    public MyProfile pin(String pin) {
	        this.pin = pin;
	        return this;
	    }

	    public void setPin(String pin) {
	        this.pin = pin;
	    }
	    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        MyProfile MyProfile = (MyProfile) o;
	        if (MyProfile.getId() == null || getId() == null) {
	            return false;
	        }
	        return Objects.equals(getId(), MyProfile.getId());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(getId());
	    }

	    @Override
	    public String toString() {
	        return "MyProfile{" +
	            "id=" + getId() +
//	            "UID="+ getUID() +
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

