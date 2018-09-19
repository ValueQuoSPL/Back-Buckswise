package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Eightycdeduct entity.
 */
public class EightycdeductDTO implements Serializable {

    private Long id;

    private Integer uid;

    private String fixed;

    private String tution;

    private String nsc;

    private String nss;

    private String reinvest;

    private String licpremium;

    private String equity;

    private String pf;

    private String ppf;

    private String other;

    private String tutionfee;

    private String ulip;

    private String post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    public String getTution() {
        return tution;
    }

    public void setTution(String tution) {
        this.tution = tution;
    }

    public String getNsc() {
        return nsc;
    }

    public void setNsc(String nsc) {
        this.nsc = nsc;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getReinvest() {
        return reinvest;
    }

    public void setReinvest(String reinvest) {
        this.reinvest = reinvest;
    }

    public String getLicpremium() {
        return licpremium;
    }

    public void setLicpremium(String licpremium) {
        this.licpremium = licpremium;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPpf() {
        return ppf;
    }

    public void setPpf(String ppf) {
        this.ppf = ppf;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getTutionfee() {
        return tutionfee;
    }

    public void setTutionfee(String tutionfee) {
        this.tutionfee = tutionfee;
    }

    public String getUlip() {
        return ulip;
    }

    public void setUlip(String ulip) {
        this.ulip = ulip;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EightycdeductDTO eightycdeductDTO = (EightycdeductDTO) o;
        if(eightycdeductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eightycdeductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EightycdeductDTO{" +
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
