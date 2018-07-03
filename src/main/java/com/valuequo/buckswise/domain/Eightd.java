package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Eightd.
 */
@Entity
@Table(name = "eightd")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Eightd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medself")
    private Integer medself;

    @Column(name = "medparents")
    private Integer medparents;

    @Column(name = "healthcheck")
    private Integer healthcheck;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMedself() {
        return medself;
    }

    public Eightd medself(Integer medself) {
        this.medself = medself;
        return this;
    }

    public void setMedself(Integer medself) {
        this.medself = medself;
    }

    public Integer getMedparents() {
        return medparents;
    }

    public Eightd medparents(Integer medparents) {
        this.medparents = medparents;
        return this;
    }

    public void setMedparents(Integer medparents) {
        this.medparents = medparents;
    }

    public Integer getHealthcheck() {
        return healthcheck;
    }

    public Eightd healthcheck(Integer healthcheck) {
        this.healthcheck = healthcheck;
        return this;
    }

    public void setHealthcheck(Integer healthcheck) {
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
        Eightd eightd = (Eightd) o;
        if (eightd.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightd.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Eightd{" +
            "id=" + getId() +
            ", medself=" + getMedself() +
            ", medparents=" + getMedparents() +
            ", healthcheck=" + getHealthcheck() +
            "}";
    }
}
