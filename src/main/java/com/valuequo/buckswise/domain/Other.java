package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Other.
 */
@Entity
@Table(name = "other")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Other implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "handicapped")
    private Integer handicapped;

    @Column(name = "medicaltreat")
    private Integer medicaltreat;

    @Column(name = "selfedu")
    private Integer selfedu;

    @Column(name = "nps")
    private Integer nps;

    @Column(name = "rgess")
    private Integer rgess;

    @Column(name = "donation")
    private Integer donation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHandicapped() {
        return handicapped;
    }

    public Other handicapped(Integer handicapped) {
        this.handicapped = handicapped;
        return this;
    }

    public void setHandicapped(Integer handicapped) {
        this.handicapped = handicapped;
    }

    public Integer getMedicaltreat() {
        return medicaltreat;
    }

    public Other medicaltreat(Integer medicaltreat) {
        this.medicaltreat = medicaltreat;
        return this;
    }

    public void setMedicaltreat(Integer medicaltreat) {
        this.medicaltreat = medicaltreat;
    }

    public Integer getSelfedu() {
        return selfedu;
    }

    public Other selfedu(Integer selfedu) {
        this.selfedu = selfedu;
        return this;
    }

    public void setSelfedu(Integer selfedu) {
        this.selfedu = selfedu;
    }

    public Integer getNps() {
        return nps;
    }

    public Other nps(Integer nps) {
        this.nps = nps;
        return this;
    }

    public void setNps(Integer nps) {
        this.nps = nps;
    }

    public Integer getRgess() {
        return rgess;
    }

    public Other rgess(Integer rgess) {
        this.rgess = rgess;
        return this;
    }

    public void setRgess(Integer rgess) {
        this.rgess = rgess;
    }

    public Integer getDonation() {
        return donation;
    }

    public Other donation(Integer donation) {
        this.donation = donation;
        return this;
    }

    public void setDonation(Integer donation) {
        this.donation = donation;
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
        Other other = (Other) o;
        if (other.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Other{" +
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
