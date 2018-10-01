package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Home.
 */
@Entity
@Table(name = "home")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Home implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "hoamloan")
    private String hoamloan;

    @Column(name = "prncpalloan")
    private String prncpalloan;

    @Column(name = "rentclm")
    private String rentclm;

    @Column(name = "remintrst")
    private String remintrst;

    @Column(name = "rentclmgg")
    private String rentclmgg;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public Home uid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getHoamloan() {
        return hoamloan;
    }

    public Home hoamloan(String hoamloan) {
        this.hoamloan = hoamloan;
        return this;
    }

    public void setHoamloan(String hoamloan) {
        this.hoamloan = hoamloan;
    }

    public String getPrncpalloan() {
        return prncpalloan;
    }

    public Home prncpalloan(String prncpalloan) {
        this.prncpalloan = prncpalloan;
        return this;
    }

    public void setPrncpalloan(String prncpalloan) {
        this.prncpalloan = prncpalloan;
    }

    public String getRentclm() {
        return rentclm;
    }

    public Home rentclm(String rentclm) {
        this.rentclm = rentclm;
        return this;
    }

    public void setRentclm(String rentclm) {
        this.rentclm = rentclm;
    }

    public String getRemintrst() {
        return remintrst;
    }

    public Home remintrst(String remintrst) {
        this.remintrst = remintrst;
        return this;
    }

    public void setRemintrst(String remintrst) {
        this.remintrst = remintrst;
    }

    public String getRentclmgg() {
        return rentclmgg;
    }

    public Home rentclmgg(String rentclmgg) {
        this.rentclmgg = rentclmgg;
        return this;
    }

    public void setRentclmgg(String rentclmgg) {
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
        Home home = (Home) o;
        if (home.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), home.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Home{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", hoamloan='" + getHoamloan() + "'" +
            ", prncpalloan='" + getPrncpalloan() + "'" +
            ", rentclm='" + getRentclm() + "'" +
            ", remintrst='" + getRemintrst() + "'" +
            ", rentclmgg='" + getRentclmgg() + "'" +
            "}";
    }
}
