package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Grossdeduct entity.
 */
public class GrossdeductDTO implements Serializable {

    private Long id;

    private Integer uid;

    private String bsalary;

    private String da;

    private String hra;

    private String conveyance;

    private String childedu;

    private String medical;

    private String lta;

    private String otherallown;

    private String bonus;

    private String rentincome;

    private String saving;

    private String bonds;

    private String conveyanceother;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getBsalary() {
        return bsalary;
    }

    public void setBsalary(String bsalary) {
        this.bsalary = bsalary;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getHra() {
        return hra;
    }

    public void setHra(String hra) {
        this.hra = hra;
    }

    public String getConveyance() {
        return conveyance;
    }

    public void setConveyance(String conveyance) {
        this.conveyance = conveyance;
    }

    public String getChildedu() {
        return childedu;
    }

    public void setChildedu(String childedu) {
        this.childedu = childedu;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getLta() {
        return lta;
    }

    public void setLta(String lta) {
        this.lta = lta;
    }

    public String getOtherallown() {
        return otherallown;
    }

    public void setOtherallown(String otherallown) {
        this.otherallown = otherallown;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getRentincome() {
        return rentincome;
    }

    public void setRentincome(String rentincome) {
        this.rentincome = rentincome;
    }

    public String getSaving() {
        return saving;
    }

    public void setSaving(String saving) {
        this.saving = saving;
    }

    public String getBonds() {
        return bonds;
    }

    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    public String getConveyanceother() {
        return conveyanceother;
    }

    public void setConveyanceother(String conveyanceother) {
        this.conveyanceother = conveyanceother;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GrossdeductDTO grossdeductDTO = (GrossdeductDTO) o;
        if(grossdeductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grossdeductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrossdeductDTO{" +
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
