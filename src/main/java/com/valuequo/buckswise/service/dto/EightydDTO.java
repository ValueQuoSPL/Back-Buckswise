package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Eightyd entity.
 */
public class EightydDTO implements Serializable {

    private Long id;

    private Integer uid;

    private String medself;

    private String medparents;

    private String healthcheck;

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

    public String getMedself() {
        return medself;
    }

    public void setMedself(String medself) {
        this.medself = medself;
    }

    public String getMedparents() {
        return medparents;
    }

    public void setMedparents(String medparents) {
        this.medparents = medparents;
    }

    public String getHealthcheck() {
        return healthcheck;
    }

    public void setHealthcheck(String healthcheck) {
        this.healthcheck = healthcheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EightydDTO eightydDTO = (EightydDTO) o;
        if(eightydDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightydDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EightydDTO{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", medself='" + getMedself() + "'" +
            ", medparents='" + getMedparents() + "'" +
            ", healthcheck='" + getHealthcheck() + "'" +
            "}";
    }
}
