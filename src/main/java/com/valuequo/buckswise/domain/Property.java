package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Property.
 */
@Entity
@Table(name = "property")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prop_name")
    private String prop_name;

    @Column(name = "prop_type")
    private String prop_type;

    @Column(name = "prop_details")
    private String prop_details;

    @Column(name = "current_m_value")
    private String current_m_value;

    @Column(name = "as_of_date")
    private LocalDate as_of_date;

    @Column(name = "notes")
    private String notes;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "available")
    private String available;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getProp_name() {
        return prop_name;
    }

    public Property prop_name(String prop_name) {
        this.prop_name = prop_name;
        return this;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public String getProp_type() {
        return prop_type;
    }

    public Property prop_type(String prop_type) {
        this.prop_type = prop_type;
        return this;
    }

    public void setProp_type(String prop_type) {
        this.prop_type = prop_type;
    }

    public String getProp_details() {
        return prop_details;
    }

    public Property prop_details(String prop_details) {
        this.prop_details = prop_details;
        return this;
    }

    public void setProp_details(String prop_details) {
        this.prop_details = prop_details;
    }

    public String getCurrent_m_value() {
        return current_m_value;
    }

    public Property current_m_value(String current_m_value) {
        this.current_m_value = current_m_value;
        return this;
    }

    public void setCurrent_m_value(String current_m_value) {
        this.current_m_value = current_m_value;
    }

    public LocalDate getAs_of_date() {
        return as_of_date;
    }

    public Property as_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
        return this;
    }

    public void setAs_of_date(LocalDate as_of_date) {
        this.as_of_date = as_of_date;
    }

    public String getNotes() {
        return notes;
    }

    public Property notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getUserid() {
        return userid;
    }

    public Property userid(Long userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
        Property property = (Property) o;
        if (property.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), property.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Property{" +
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
