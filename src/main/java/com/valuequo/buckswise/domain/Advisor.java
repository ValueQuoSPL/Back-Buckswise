package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Advisor.
 */
@Entity
@Table(name = "advisor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Advisor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "aid")
    private Long aid;

    @Column(name = "recotype")
    private String recotype;

    @Column(name = "reco")
    private String reco;

    @Column(name = "recoby")
    private String recoby;

    @Column(name = "recodate")
    private String recodate;

    @Column(name = "userresponse")
    private String userresponse;

    @Column(name = "usercomment")
    private String usercomment;

    @Column(name = "reject")
    private String reject;

    @Column(name = "approve")
    private String approve;

    

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public Advisor uid(Long uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAid() {
        return aid;
    }

    public Advisor aid(Long aid) {
        this.aid = aid;
        return this;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getRecotype() {
        return recotype;
    }

    public Advisor recotype(String recotype) {
        this.recotype = recotype;
        return this;
    }

    public void setRecotype(String recotype) {
        this.recotype = recotype;
    }

    public String getReco() {
        return reco;
    }

    public Advisor reco(String reco) {
        this.reco = reco;
        return this;
    }

    public void setReco(String reco) {
        this.reco = reco;
    }

    public String getRecoby() {
        return recoby;
    }

    public Advisor recoby(String recoby) {
        this.recoby = recoby;
        return this;
    }

    public void setRecoby(String recoby) {
        this.recoby = recoby;
    }

    public String getRecodate() {
        return recodate;
    }

    public Advisor recodate(String recodate) {
        this.recodate = recodate;
        return this;
    }

    public void setRecodate(String recodate) {
        this.recodate = recodate;
    }

    public String getUserresponse() {
        return userresponse;
    }

    public Advisor userresponse(String userresponse) {
        this.userresponse = userresponse;
        return this;
    }

    public void setUserresponse(String userresponse) {
        this.userresponse = userresponse;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public Advisor usercomment(String usercomment) {
        this.usercomment = usercomment;
        return this;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
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
        Advisor advisor = (Advisor) o;
        if (advisor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), advisor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Advisor{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", recotype='" + getRecotype() + "'" +
            ", reco='" + getReco() + "'" +
            ", recoby='" + getRecoby() + "'" +
            ", recodate='" + getRecodate() + "'" +
            ", userresponse='" + getUserresponse() + "'" +
            "}";
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }
}
