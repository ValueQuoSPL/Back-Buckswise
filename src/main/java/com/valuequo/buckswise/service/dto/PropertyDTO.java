package com.valuequo.buckswise.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Property entity.
 */
public class PropertyDTO implements Serializable {

    private Long id;

    private String prop_name;

    private String prop_type;

    private String prop_details;

    private String current_m_value;

    private LocalDate as_of_date;

    private String notes;

    private Long userid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public String getProp_type() {
        return prop_type;
    }

    public void setProp_type(String prop_type) {
        this.prop_type = prop_type;
    }

    public String getProp_details() {
        return prop_details;
    }

    public void setProp_details(String prop_details) {
        this.prop_details = prop_details;
    }

    public String getCurrent_m_value() {
        return current_m_value;
    }

    public void setCurrent_m_value(String current_m_value) {
        this.current_m_value = current_m_value;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

        PropertyDTO propertyDTO = (PropertyDTO) o;
        if(propertyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), propertyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PropertyDTO{" +
            "id=" + getId() +
            ", prop_name='" + getProp_name() + "'" +
            ", prop_type='" + getProp_type() + "'" +
            ", prop_details='" + getProp_details() + "'" +
            ", current_m_value='" + getCurrent_m_value() + "'" +
            ", as_of_date='" + getAs_of_date() + "'" +
            ", notes='" + getNotes() + "'" +
            ", userid=" + getUserid() +
            "}";
    }
}
