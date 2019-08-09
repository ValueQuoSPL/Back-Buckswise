package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Advisor entity.
 */
public class AdvisorDTO implements Serializable {

    private Long id;

    private Long uid;

    private Long aid;

    private String recotype;

    private String reco;

    private String recoby;

    private String recodate;

    private String userresponse;

    private String usercomment;

    private String approve;

    private String reject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getRecotype() {
        return recotype;
    }

    public void setRecotype(String recotype) {
        this.recotype = recotype;
    }

    public String getReco() {
        return reco;
    }

    public void setReco(String reco) {
        this.reco = reco;
    }

    public String getRecoby() {
        return recoby;
    }

    public void setRecoby(String recoby) {
        this.recoby = recoby;
    }

    public String getRecodate() {
        return recodate;
    }

    public void setRecodate(String recodate) {
        this.recodate = recodate;
    }

    public String getUserresponse() {
        return userresponse;
    }

    public void setUserresponse(String userresponse) {
        this.userresponse = userresponse;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdvisorDTO advisorDTO = (AdvisorDTO) o;
        if(advisorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), advisorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdvisorDTO{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", recotype='" + getRecotype() + "'" +
            ", reco='" + getReco() + "'" +
            ", recoby='" + getRecoby() + "'" +
            ", recodate='" + getRecodate() + "'" +
            ", userresponse='" + getUserresponse() + "'" +
            ", usercomment='" + getUsercomment() + "'" +
            "}";
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }
}
