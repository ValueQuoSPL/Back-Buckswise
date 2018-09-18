package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Grossdeduct.
 */
@Entity
@Table(name = "grossdeduct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Grossdeduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "bsalary")
    private String bsalary;

    @Column(name = "da")
    private String da;

    @Column(name = "hra")
    private String hra;

    @Column(name = "conveyance")
    private String conveyance;

    @Column(name = "childedu")
    private String childedu;

    @Column(name = "medical")
    private String medical;

    @Column(name = "lta")
    private String lta;

    @Column(name = "otherallown")
    private String otherallown;

    @Column(name = "bonus")
    private String bonus;

    @Column(name = "rentincome")
    private String rentincome;

    @Column(name = "saving")
    private String saving;

    @Column(name = "bonds")
    private String bonds;

    @Column(name = "conveyanceother")
    private String conveyanceother;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public Grossdeduct uid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBsalary() {
        return bsalary;
    }

    public Grossdeduct bsalary(String bsalary) {
        this.bsalary = bsalary;
        return this;
    }

    public void setBsalary(String bsalary) {
        this.bsalary = bsalary;
    }

    public String getDa() {
        return da;
    }

    public Grossdeduct da(String da) {
        this.da = da;
        return this;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getHra() {
        return hra;
    }

    public Grossdeduct hra(String hra) {
        this.hra = hra;
        return this;
    }

    public void setHra(String hra) {
        this.hra = hra;
    }

    public String getConveyance() {
        return conveyance;
    }

    public Grossdeduct conveyance(String conveyance) {
        this.conveyance = conveyance;
        return this;
    }

    public void setConveyance(String conveyance) {
        this.conveyance = conveyance;
    }

    public String getChildedu() {
        return childedu;
    }

    public Grossdeduct childedu(String childedu) {
        this.childedu = childedu;
        return this;
    }

    public void setChildedu(String childedu) {
        this.childedu = childedu;
    }

    public String getMedical() {
        return medical;
    }

    public Grossdeduct medical(String medical) {
        this.medical = medical;
        return this;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getLta() {
        return lta;
    }

    public Grossdeduct lta(String lta) {
        this.lta = lta;
        return this;
    }

    public void setLta(String lta) {
        this.lta = lta;
    }

    public String getOtherallown() {
        return otherallown;
    }

    public Grossdeduct otherallown(String otherallown) {
        this.otherallown = otherallown;
        return this;
    }

    public void setOtherallown(String otherallown) {
        this.otherallown = otherallown;
    }

    public String getBonus() {
        return bonus;
    }

    public Grossdeduct bonus(String bonus) {
        this.bonus = bonus;
        return this;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getRentincome() {
        return rentincome;
    }

    public Grossdeduct rentincome(String rentincome) {
        this.rentincome = rentincome;
        return this;
    }

    public void setRentincome(String rentincome) {
        this.rentincome = rentincome;
    }

    public String getSaving() {
        return saving;
    }

    public Grossdeduct saving(String saving) {
        this.saving = saving;
        return this;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }

    public String getBonds() {
        return bonds;
    }

    public Grossdeduct bonds(String bonds) {
        this.bonds = bonds;
        return this;
    }

    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    public String getConveyanceother() {
        return conveyanceother;
    }

    public Grossdeduct conveyanceother(String conveyanceother) {
        this.conveyanceother = conveyanceother;
        return this;
    }

    public void setConveyanceother(String conveyanceother) {
        this.conveyanceother = conveyanceother;
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
        Grossdeduct grossdeduct = (Grossdeduct) o;
        if (grossdeduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grossdeduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Grossdeduct{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", bsalary='" + getBsalary() + "'" +
            ", da='" + getDa() + "'" +
            ", hra='" + getHra() + "'" +
            ", conveyance='" + getConveyance() + "'" +
            ", childedu='" + getChildedu() + "'" +
            ", medical='" + getMedical() + "'" +
            ", lta='" + getLta() + "'" +
            ", otherallown='" + getOtherallown() + "'" +
            ", bonus='" + getBonus() + "'" +
            ", rentincome='" + getRentincome() + "'" +
            ", saving='" + getSaving() + "'" +
            ", bonds='" + getBonds() + "'" +
            ", conveyanceother='" + getConveyanceother() + "'" +
            "}";
    }
}
