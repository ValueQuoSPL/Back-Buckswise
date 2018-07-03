package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Homededuction.
 */
@Entity
@Table(name = "homededuction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Homededuction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "homeloan")
    private Integer homeloan;

    @Column(name = "prncpalloan")
    private Integer prncpalloan;

    @Column(name = "rentclm")
    private Integer rentclm;

    @Column(name = "remintrst")
    private Integer remintrst;

    @Column(name = "rentclmgg")
    private Integer rentclmgg;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeloan() {
        return homeloan;
    }

    public Homededuction homeloan(Integer homeloan) {
        this.homeloan = homeloan;
        return this;
    }

    public void setHomeloan(Integer homeloan) {
        this.homeloan = homeloan;
    }

    public Integer getPrncpalloan() {
        return prncpalloan;
    }

    public Homededuction prncpalloan(Integer prncpalloan) {
        this.prncpalloan = prncpalloan;
        return this;
    }

    public void setPrncpalloan(Integer prncpalloan) {
        this.prncpalloan = prncpalloan;
    }

    public Integer getRentclm() {
        return rentclm;
    }

    public Homededuction rentclm(Integer rentclm) {
        this.rentclm = rentclm;
        return this;
    }

    public void setRentclm(Integer rentclm) {
        this.rentclm = rentclm;
    }

    public Integer getRemintrst() {
        return remintrst;
    }

    public Homededuction remintrst(Integer remintrst) {
        this.remintrst = remintrst;
        return this;
    }

    public void setRemintrst(Integer remintrst) {
        this.remintrst = remintrst;
    }

    public Integer getRentclmgg() {
        return rentclmgg;
    }

    public Homededuction rentclmgg(Integer rentclmgg) {
        this.rentclmgg = rentclmgg;
        return this;
    }

    public void setRentclmgg(Integer rentclmgg) {
        this.rentclmgg = rentclmgg;
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
        Homededuction homededuction = (Homededuction) o;
        if (homededuction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), homededuction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Homededuction{" +
            "id=" + getId() +
            ", homeloan=" + getHomeloan() +
            ", prncpalloan=" + getPrncpalloan() +
            ", rentclm=" + getRentclm() +
            ", remintrst=" + getRemintrst() +
            ", rentclmgg=" + getRentclmgg() +
            "}";
    }
}
