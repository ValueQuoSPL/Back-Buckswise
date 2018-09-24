package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A Familyprofile.
 */
@Entity
@Table(name = "familyprofile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Familyprofile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "uid")
    private long uid;
    
    @Column(name = "relationship")
    private String relationship;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "is_earning")
    private String earncheck;

	@Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phonenumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getRelationship() {
        return relationship;
    }

    public Familyprofile relationship(String relationship) {
        this.relationship = relationship;
        return this;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFirstname() {
        return firstname;
    }

    public Familyprofile firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public Familyprofile middlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public Familyprofile lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Familyprofile dateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
//
//    public String getIsEarning() {
//        return earncheck;
//    }
//
//    public Familyprofile isEarning(String earncheck) {
//        this.earncheck = earncheck;
//        return this;
//    }
//
//    public void setIsEarning(String earncheck) {
//        this.earncheck = earncheck;
//    }

    public String getEmail() {
        return email;
    }

    public String getEarncheck() {
		return earncheck;
	}

	public void setEarncheck(String earncheck) {
		this.earncheck = earncheck;
	}
	 public Familyprofile earncheck(String earncheck) {
       this.earncheck = earncheck;
       return this;
   }
	

	public Familyprofile email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Familyprofile phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
        Familyprofile familyprofile = (Familyprofile) o;
        if (familyprofile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), familyprofile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Familyprofile{" +
            "id=" + getId() +
            "uid= "+ getUid() +
            ",relationship='" + getRelationship() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", middlename='" + getMiddlename() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", isEarning='" + getEarncheck() + "'" +
            ", email='" + getEmail() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            "}";
    }

	public Stream<Familyprofile> map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
