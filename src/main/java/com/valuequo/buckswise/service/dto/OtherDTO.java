package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Other entity.
 */
public class OtherDTO implements Serializable {

    private Long id;

    private Integer handicapped;

    private Integer medicaltreat;

    private Integer selfedu;

    private Integer nps;

    private Integer rgess;

    private Integer donation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHandicapped() {
        return handicapped;
    }

    public void setHandicapped(Integer handicapped) {
        this.handicapped = handicapped;
    }

    public Integer getMedicaltreat() {
        return medicaltreat;
    }

    public void setMedicaltreat(Integer medicaltreat) {
        this.medicaltreat = medicaltreat;
    }

    public Integer getSelfedu() {
        return selfedu;
    }

    public void setSelfedu(Integer selfedu) {
        this.selfedu = selfedu;
    }

    public Integer getNps() {
        return nps;
    }

    public void setNps(Integer nps) {
        this.nps = nps;
    }

    public Integer getRgess() {
        return rgess;
    }

    public void setRgess(Integer rgess) {
        this.rgess = rgess;
    }

    public Integer getDonation() {
        return donation;
    }

    public void setDonation(Integer donation) {
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

        OtherDTO otherDTO = (OtherDTO) o;
        if(otherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), otherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OtherDTO{" +
            "id=" + getId() +
            ", handicapped=" + getHandicapped() +
            ", medicaltreat=" + getMedicaltreat() +
            ", selfedu=" + getSelfedu() +
            ", nps=" + getNps() +
            ", rgess=" + getRgess() +
            ", donation=" + getDonation() +
            "}";
    }
}
