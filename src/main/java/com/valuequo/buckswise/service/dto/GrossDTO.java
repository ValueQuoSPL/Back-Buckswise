package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Gross entity.
 */
public class GrossDTO implements Serializable {

    private Long id;

    private Integer bsalary;

    private Integer da;

    private Integer hra;

    private Integer conveyance;

    private Integer childedu;

    private Integer medical;

    private Integer lta;

    private Integer otherallown;

    private Integer bonus;

    private Integer rentincome;

    private Integer saving;

    private Integer bonds;

    private Integer conveyanceother;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBsalary() {
        return bsalary;
    }

    public void setBsalary(Integer bsalary) {
        this.bsalary = bsalary;
    }

    public Integer getDa() {
        return da;
    }

    public void setDa(Integer da) {
        this.da = da;
    }

    public Integer getHra() {
        return hra;
    }

    public void setHra(Integer hra) {
        this.hra = hra;
    }

    public Integer getConveyance() {
        return conveyance;
    }

    public void setConveyance(Integer conveyance) {
        this.conveyance = conveyance;
    }

    public Integer getChildedu() {
        return childedu;
    }

    public void setChildedu(Integer childedu) {
        this.childedu = childedu;
    }

    public Integer getMedical() {
        return medical;
    }

    public void setMedical(Integer medical) {
        this.medical = medical;
    }

    public Integer getLta() {
        return lta;
    }

    public void setLta(Integer lta) {
        this.lta = lta;
    }

    public Integer getOtherallown() {
        return otherallown;
    }

    public void setOtherallown(Integer otherallown) {
        this.otherallown = otherallown;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getRentincome() {
        return rentincome;
    }

    public void setRentincome(Integer rentincome) {
        this.rentincome = rentincome;
    }

    public Integer getSaving() {
        return saving;
    }

    public void setSaving(Integer saving) {
        this.saving = saving;
    }

    public Integer getBonds() {
        return bonds;
    }

    public void setBonds(Integer bonds) {
        this.bonds = bonds;
    }

    public Integer getConveyanceother() {
        return conveyanceother;
    }

    public void setConveyanceother(Integer conveyanceother) {
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

        GrossDTO grossDTO = (GrossDTO) o;
        if(grossDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grossDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrossDTO{" +
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
