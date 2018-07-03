package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Eightyc.
 */
@Entity
@Table(name = "eightyc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Eightyc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fixed")
    private Integer fixed;

    @Column(name = "tution")
    private Integer tution;

    @Column(name = "nsc")
    private Integer nsc;

    @Column(name = "nss")
    private Integer nss;

    @Column(name = "post")
    private Integer post;

    @Column(name = "reinvest")
    private Integer reinvest;

    @Column(name = "licpremium")
    private Integer licpremium;

    @Column(name = "equity")
    private Integer equity;

    @Column(name = "pf")
    private Integer pf;

    @Column(name = "ppf")
    private Integer ppf;

    @Column(name = "other")
    private Integer other;

    @Column(name = "tutionfee")
    private Integer tutionfee;

    @Column(name = "ulip")
    private Integer ulip;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFixed() {
        return fixed;
    }

    public Eightyc fixed(Integer fixed) {
        this.fixed = fixed;
        return this;
    }

    public void setFixed(Integer fixed) {
        this.fixed = fixed;
    }

    public Integer getTution() {
        return tution;
    }

    public Eightyc tution(Integer tution) {
        this.tution = tution;
        return this;
    }

    public void setTution(Integer tution) {
        this.tution = tution;
    }

    public Integer getNsc() {
        return nsc;
    }

    public Eightyc nsc(Integer nsc) {
        this.nsc = nsc;
        return this;
    }

    public void setNsc(Integer nsc) {
        this.nsc = nsc;
    }

    public Integer getNss() {
        return nss;
    }

    public Eightyc nss(Integer nss) {
        this.nss = nss;
        return this;
    }

    public void setNss(Integer nss) {
        this.nss = nss;
    }

    public Integer getPost() {
        return post;
    }

    public Eightyc post(Integer post) {
        this.post = post;
        return this;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getReinvest() {
        return reinvest;
    }

    public Eightyc reinvest(Integer reinvest) {
        this.reinvest = reinvest;
        return this;
    }

    public void setReinvest(Integer reinvest) {
        this.reinvest = reinvest;
    }

    public Integer getLicpremium() {
        return licpremium;
    }

    public Eightyc licpremium(Integer licpremium) {
        this.licpremium = licpremium;
        return this;
    }

    public void setLicpremium(Integer licpremium) {
        this.licpremium = licpremium;
    }

    public Integer getEquity() {
        return equity;
    }

    public Eightyc equity(Integer equity) {
        this.equity = equity;
        return this;
    }

    public void setEquity(Integer equity) {
        this.equity = equity;
    }

    public Integer getPf() {
        return pf;
    }

    public Eightyc pf(Integer pf) {
        this.pf = pf;
        return this;
    }

    public void setPf(Integer pf) {
        this.pf = pf;
    }

    public Integer getPpf() {
        return ppf;
    }

    public Eightyc ppf(Integer ppf) {
        this.ppf = ppf;
        return this;
    }

    public void setPpf(Integer ppf) {
        this.ppf = ppf;
    }

    public Integer getOther() {
        return other;
    }

    public Eightyc other(Integer other) {
        this.other = other;
        return this;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getTutionfee() {
        return tutionfee;
    }

    public Eightyc tutionfee(Integer tutionfee) {
        this.tutionfee = tutionfee;
        return this;
    }

    public void setTutionfee(Integer tutionfee) {
        this.tutionfee = tutionfee;
    }

    public Integer getUlip() {
        return ulip;
    }

    public Eightyc ulip(Integer ulip) {
        this.ulip = ulip;
        return this;
    }

    public void setUlip(Integer ulip) {
        this.ulip = ulip;
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
        Eightyc eightyc = (Eightyc) o;
        if (eightyc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightyc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Eightyc{" +
            "id=" + getId() +
            ", fixed=" + getFixed() +
            ", tution=" + getTution() +
            ", nsc=" + getNsc() +
            ", nss=" + getNss() +
            ", post=" + getPost() +
            ", reinvest=" + getReinvest() +
            ", licpremium=" + getLicpremium() +
            ", equity=" + getEquity() +
            ", pf=" + getPf() +
            ", ppf=" + getPpf() +
            ", other=" + getOther() +
            ", tutionfee=" + getTutionfee() +
            ", ulip=" + getUlip() +
            "}";
    }
}
