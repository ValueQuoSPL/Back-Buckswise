package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Family entity.
 */
public class FamilyDTO implements Serializable {

    private Long id;

    private String firstName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamilyDTO familyDTO = (FamilyDTO) o;
        if(familyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), familyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FamilyDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            "}";
    }
}
