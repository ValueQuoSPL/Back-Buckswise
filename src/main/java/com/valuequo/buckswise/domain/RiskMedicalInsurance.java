package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RiskMedicalInsurance.
 */
@Entity
@Table(name = "risk_medical_insurance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RiskMedicalInsurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "family_members")
    private String family_members;

    @Column(name = "hosp_type")
    private String hosp_type;

    @Column(name = "room_type")
    private String room_type;

    @Column(name = "userid")
    private Long userid;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamily_members() {
        return family_members;
    }

    public RiskMedicalInsurance family_members(String family_members) {
        this.family_members = family_members;
        return this;
    }

    public void setFamily_members(String family_members) {
        this.family_members = family_members;
    }

    public String getHosp_type() {
        return hosp_type;
    }

    public RiskMedicalInsurance hosp_type(String hosp_type) {
        this.hosp_type = hosp_type;
        return this;
    }

    public void setHosp_type(String hosp_type) {
        this.hosp_type = hosp_type;
    }

    public String getRoom_type() {
        return room_type;
    }

    public RiskMedicalInsurance room_type(String room_type) {
        this.room_type = room_type;
        return this;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Long getUserid() {
        return userid;
    }

    public RiskMedicalInsurance userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
        RiskMedicalInsurance riskMedicalInsurance = (RiskMedicalInsurance) o;
        if (riskMedicalInsurance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskMedicalInsurance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskMedicalInsurance{" +
            "id=" + getId() +
            ", family_members='" + getFamily_members() + "'" +
            ", hosp_type='" + getHosp_type() + "'" +
            ", room_type='" + getRoom_type() + "'" +
            ", userid='" + getUserid() + "'" +
            "}";
    }
}
