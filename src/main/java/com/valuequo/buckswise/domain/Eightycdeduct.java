package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Eightycdeduct.
 */
@Entity
@Table(name = "eightycdeduct")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Eightycdeduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "fixed")
    private String fixed;

    @Column(name = "tution")
    private String tution;

    @Column(name = "nsc")
    private String nsc;

    @Column(name = "nss")
    private String nss;

    @Column(name = "reinvest")
    private String reinvest;

    @Column(name = "licpremium")
    private String licpremium;

    @Column(name = "equity")
    private String equity;

    @Column(name = "pf")
    private String pf;

    @Column(name = "ppf")
    private String ppf;

    @Column(name = "other")
    private String other;

    @Column(name = "tutionfee")
    private String tutionfee;

    @Column(name = "ulip")
    private String ulip;

    @Column(name = "post")
    private String post;

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

    public Eightycdeduct uid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFixed() {
        return fixed;
    }

    public Eightycdeduct fixed(String fixed) {
        this.fixed = fixed;
        return this;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getTution() {
        return tution;
    }

    public Eightycdeduct tution(String tution) {
        this.tution = tution;
        return this;
    }

    public void setTution(String tution) {
        this.tution = tution;
    }

    public String getNsc() {
        return nsc;
    }

    public Eightycdeduct nsc(String nsc) {
        this.nsc = nsc;
        return this;
    }

    public void setNsc(String nsc) {
        this.nsc = nsc;
    }

    public String getNss() {
        return nss;
    }

    public Eightycdeduct nss(String nss) {
        this.nss = nss;
        return this;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getReinvest() {
        return reinvest;
    }

    public Eightycdeduct reinvest(String reinvest) {
        this.reinvest = reinvest;
        return this;
    }

    public void setReinvest(String reinvest) {
        this.reinvest = reinvest;
    }

    public String getLicpremium() {
        return licpremium;
    }

    public Eightycdeduct licpremium(String licpremium) {
        this.licpremium = licpremium;
        return this;
    }

    public void setLicpremium(String licpremium) {
        this.licpremium = licpremium;
    }

    public String getEquity() {
        return equity;
    }

    public Eightycdeduct equity(String equity) {
        this.equity = equity;
        return this;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getPf() {
        return pf;
    }

    public Eightycdeduct pf(String pf) {
        this.pf = pf;
        return this;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPpf() {
        return ppf;
    }

    public Eightycdeduct ppf(String ppf) {
        this.ppf = ppf;
        return this;
    }

    public void setPpf(String ppf) {
        this.ppf = ppf;
    }

    public String getOther() {
        return other;
    }

    public Eightycdeduct other(String other) {
        this.other = other;
        return this;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getTutionfee() {
        return tutionfee;
    }

    public Eightycdeduct tutionfee(String tutionfee) {
        this.tutionfee = tutionfee;
        return this;
    }

    public void setTutionfee(String tutionfee) {
        this.tutionfee = tutionfee;
    }

    public String getUlip() {
        return ulip;
    }

    public Eightycdeduct ulip(String ulip) {
        this.ulip = ulip;
        return this;
    }

    public void setUlip(String ulip) {
        this.ulip = ulip;
    }

    public String getPost() {
        return post;
    }

    public Eightycdeduct post(String post) {
        this.post = post;
        return this;
    }

    public void setPost(String post) {
        this.post = post;
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
        Eightycdeduct eightycdeduct = (Eightycdeduct) o;
        if (eightycdeduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightycdeduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Eightycdeduct{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", fixed='" + getFixed() + "'" +
            ", tution='" + getTution() + "'" +
            ", nsc='" + getNsc() + "'" +
            ", nss='" + getNss() + "'" +
            ", reinvest='" + getReinvest() + "'" +
            ", licpremium='" + getLicpremium() + "'" +
            ", equity='" + getEquity() + "'" +
            ", pf='" + getPf() + "'" +
            ", ppf='" + getPpf() + "'" +
            ", other='" + getOther() + "'" +
            ", tutionfee='" + getTutionfee() + "'" +
            ", ulip='" + getUlip() + "'" +
            ", post='" + getPost() + "'" +
            "}";
    }
}
