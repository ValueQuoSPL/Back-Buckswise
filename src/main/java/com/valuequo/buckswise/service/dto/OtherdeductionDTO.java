package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Otherdeduction entity.
 */
public class OtherdeductionDTO implements Serializable {

    private Long id;

    private Integer uid;

    private String handicapped;

    private String medicaltreat;

    private String selfedu;

    private String nps;

    private String rgess;

    private String donation;

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

    public String getHandicapped() {
        return handicapped;
    }

    public void setHandicapped(String handicapped) {
        this.handicapped = handicapped;
    }

    public String getMedicaltreat() {
        return medicaltreat;
    }

    public void setMedicaltreat(String medicaltreat) {
        this.medicaltreat = medicaltreat;
    }

    public String getSelfedu() {
        return selfedu;
    }

    public void setSelfedu(String selfedu) {
        this.selfedu = selfedu;
    }

    public String getNps() {
        return nps;
    }

    public void setNps(String nps) {
        this.nps = nps;
    }

    public String getRgess() {
        return rgess;
    }

    public void setRgess(String rgess) {
        this.rgess = rgess;
    }

    public String getDonation() {
        return donation;
    }

    public void setDonation(String donation) {
        this.donation = donation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OtherdeductionDTO otherdeductionDTO = (OtherdeductionDTO) o;
        if(otherdeductionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), otherdeductionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OtherdeductionDTO{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", handicapped='" + getHandicapped() + "'" +
            ", medicaltreat='" + getMedicaltreat() + "'" +
            ", selfedu='" + getSelfedu() + "'" +
            ", nps='" + getNps() + "'" +
            ", rgess='" + getRgess() + "'" +
            ", donation='" + getDonation() + "'" +
            "}";
    }
}
