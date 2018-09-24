package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RiskMedicalInsurance entity.
 */
public class RiskMedicalInsuranceDTO implements Serializable {

    private Long id;

    private String family_members;

    private String hosp_type;

    private String room_type;

    private Long userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamily_members() {
        return family_members;
    }

    public void setFamily_members(String family_members) {
        this.family_members = family_members;
    }

    public String getHosp_type() {
        return hosp_type;
    }

    public void setHosp_type(String hosp_type) {
        this.hosp_type = hosp_type;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RiskMedicalInsuranceDTO riskMedicalInsuranceDTO = (RiskMedicalInsuranceDTO) o;
        if(riskMedicalInsuranceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskMedicalInsuranceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskMedicalInsuranceDTO{" +
            "id=" + getId() +
            ", family_members='" + getFamily_members() + "'" +
            ", hosp_type='" + getHosp_type() + "'" +
            ", room_type='" + getRoom_type() + "'" +
            ", userid='" + getUserid() + "'" +
            "}";
    }
}
