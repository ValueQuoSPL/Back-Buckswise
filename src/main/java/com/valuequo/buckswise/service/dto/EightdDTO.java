package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Eightd entity.
 */
public class EightdDTO implements Serializable {

    private Long id;

    private Integer medself;

    private Integer medparents;

    private Integer healthcheck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedself() {
        return medself;
    }

    public void setMedself(Integer medself) {
        this.medself = medself;
    }

    public Integer getMedparents() {
        return medparents;
    }

    public void setMedparents(Integer medparents) {
        this.medparents = medparents;
    }

    public Integer getHealthcheck() {
        return healthcheck;
    }

    public void setHealthcheck(Integer healthcheck) {
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

        EightdDTO eightdDTO = (EightdDTO) o;
        if(eightdDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightdDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EightdDTO{" +
            "id=" + getId() +
            ", medself=" + getMedself() +
            ", medparents=" + getMedparents() +
            ", healthcheck=" + getHealthcheck() +
            "}";
    }
}
