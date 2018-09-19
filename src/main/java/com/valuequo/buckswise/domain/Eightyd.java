package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Eightyd.
 */
@Entity
@Table(name = "eightyd")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Eightyd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "medself")
    private String medself;

    @Column(name = "medparents")
    private String medparents;

    @Column(name = "healthcheck")
    private String healthcheck;

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

    public Eightyd uid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMedself() {
        return medself;
    }

    public Eightyd medself(String medself) {
        this.medself = medself;
        return this;
    }

    public void setMedself(String medself) {
        this.medself = medself;
    }

    public String getMedparents() {
        return medparents;
    }

    public Eightyd medparents(String medparents) {
        this.medparents = medparents;
        return this;
    }

    public void setMedparents(String medparents) {
        this.medparents = medparents;
    }

    public String getHealthcheck() {
        return healthcheck;
    }

    public Eightyd healthcheck(String healthcheck) {
        this.healthcheck = healthcheck;
        return this;
    }

    public void setHealthcheck(String healthcheck) {
        this.healthcheck = healthcheck;
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
        Eightyd eightyd = (Eightyd) o;
        if (eightyd.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightyd.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Eightyd{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", medself='" + getMedself() + "'" +
            ", medparents='" + getMedparents() + "'" +
            ", healthcheck='" + getHealthcheck() + "'" +
            "}";
    }
}
