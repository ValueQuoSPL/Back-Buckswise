package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Gross.
 */
@Entity
@Table(name = "gross")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Gross implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bsalary")
    private Integer bsalary;

    @Column(name = "da")
    private Integer da;

    @Column(name = "hra")
    private Integer hra;

    @Column(name = "conveyance")
    private Integer conveyance;

    @Column(name = "childedu")
    private Integer childedu;

    @Column(name = "medical")
    private Integer medical;

    @Column(name = "lta")
    private Integer lta;

    @Column(name = "otherallown")
    private Integer otherallown;

    @Column(name = "bonus")
    private Integer bonus;

    @Column(name = "rentincome")
    private Integer rentincome;

    @Column(name = "saving")
    private Integer saving;

    @Column(name = "bonds")
    private Integer bonds;

    @Column(name = "conveyanceother")
    private Integer conveyanceother;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBsalary() {
        return bsalary;
    }

    public Gross bsalary(Integer bsalary) {
        this.bsalary = bsalary;
        return this;
    }

    public void setBsalary(Integer bsalary) {
        this.bsalary = bsalary;
    }

    public Integer getDa() {
        return da;
    }

    public Gross da(Integer da) {
        this.da = da;
        return this;
    }

    public void setDa(Integer da) {
        this.da = da;
    }

    public Integer getHra() {
        return hra;
    }

    public Gross hra(Integer hra) {
        this.hra = hra;
        return this;
    }

    public void setHra(Integer hra) {
        this.hra = hra;
    }

    public Integer getConveyance() {
        return conveyance;
    }

    public Gross conveyance(Integer conveyance) {
        this.conveyance = conveyance;
        return this;
    }

    public void setConveyance(Integer conveyance) {
        this.conveyance = conveyance;
    }

    public Integer getChildedu() {
        return childedu;
    }

    public Gross childedu(Integer childedu) {
        this.childedu = childedu;
        return this;
    }

    public void setChildedu(Integer childedu) {
        this.childedu = childedu;
    }

    public Integer getMedical() {
        return medical;
    }

    public Gross medical(Integer medical) {
        this.medical = medical;
        return this;
    }

    public void setMedical(Integer medical) {
        this.medical = medical;
    }

    public Integer getLta() {
        return lta;
    }

    public Gross lta(Integer lta) {
        this.lta = lta;
        return this;
    }

    public void setLta(Integer lta) {
        this.lta = lta;
    }

    public Integer getOtherallown() {
        return otherallown;
    }

    public Gross otherallown(Integer otherallown) {
        this.otherallown = otherallown;
        return this;
    }

    public void setOtherallown(Integer otherallown) {
        this.otherallown = otherallown;
    }

    public Integer getBonus() {
        return bonus;
    }

    public Gross bonus(Integer bonus) {
        this.bonus = bonus;
        return this;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getRentincome() {
        return rentincome;
    }

    public Gross rentincome(Integer rentincome) {
        this.rentincome = rentincome;
        return this;
    }

    public void setRentincome(Integer rentincome) {
        this.rentincome = rentincome;
    }

    public Integer getSaving() {
        return saving;
    }

    public Gross saving(Integer saving) {
        this.saving = saving;
        return this;
    }

    public void setSaving(Integer saving) {
        this.saving = saving;
    }

    public Integer getBonds() {
        return bonds;
    }

    public Gross bonds(Integer bonds) {
        this.bonds = bonds;
        return this;
    }

    public void setBonds(Integer bonds) {
        this.bonds = bonds;
    }

    public Integer getConveyanceother() {
        return conveyanceother;
    }

    public Gross conveyanceother(Integer conveyanceother) {
        this.conveyanceother = conveyanceother;
        return this;
    }

    public void setConveyanceother(Integer conveyanceother) {
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
        Gross gross = (Gross) o;
        if (gross.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gross.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gross{" +
            "id=" + getId() +
            ", bsalary=" + getBsalary() +
            ", da=" + getDa() +
            ", hra=" + getHra() +
            ", conveyance=" + getConveyance() +
            ", childedu=" + getChildedu() +
            ", medical=" + getMedical() +
            ", lta=" + getLta() +
            ", otherallown=" + getOtherallown() +
            ", bonus=" + getBonus() +
            ", rentincome=" + getRentincome() +
            ", saving=" + getSaving() +
            ", bonds=" + getBonds() +
            ", conveyanceother=" + getConveyanceother() +
            "}";
    }
}
