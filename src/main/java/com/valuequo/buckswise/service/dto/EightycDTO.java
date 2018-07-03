package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Eightyc entity.
 */
public class EightycDTO implements Serializable {

    private Long id;

    private Integer fixed;

    private Integer tution;

    private Integer nsc;

    private Integer nss;

    private Integer post;

    private Integer reinvest;

    private Integer licpremium;

    private Integer equity;

    private Integer pf;

    private Integer ppf;

    private Integer other;

    private Integer tutionfee;

    private Integer ulip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFixed() {
        return fixed;
    }

    public void setFixed(Integer fixed) {
        this.fixed = fixed;
    }

    public Integer getTution() {
        return tution;
    }

    public void setTution(Integer tution) {
        this.tution = tution;
    }

    public Integer getNsc() {
        return nsc;
    }

    public void setNsc(Integer nsc) {
        this.nsc = nsc;
    }

    public Integer getNss() {
        return nss;
    }

    public void setNss(Integer nss) {
        this.nss = nss;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getReinvest() {
        return reinvest;
    }

    public void setReinvest(Integer reinvest) {
        this.reinvest = reinvest;
    }

    public Integer getLicpremium() {
        return licpremium;
    }

    public void setLicpremium(Integer licpremium) {
        this.licpremium = licpremium;
    }

    public Integer getEquity() {
        return equity;
    }

    public void setEquity(Integer equity) {
        this.equity = equity;
    }

    public Integer getPf() {
        return pf;
    }

    public void setPf(Integer pf) {
        this.pf = pf;
    }

    public Integer getPpf() {
        return ppf;
    }

    public void setPpf(Integer ppf) {
        this.ppf = ppf;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getTutionfee() {
        return tutionfee;
    }

    public void setTutionfee(Integer tutionfee) {
        this.tutionfee = tutionfee;
    }

    public Integer getUlip() {
        return ulip;
    }

    public void setUlip(Integer ulip) {
        this.ulip = ulip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EightycDTO eightycDTO = (EightycDTO) o;
        if(eightycDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightycDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EightycDTO{" +
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
