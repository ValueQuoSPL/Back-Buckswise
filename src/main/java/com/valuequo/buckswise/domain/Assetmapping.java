package com.valuequo.buckswise.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Assetmapping.
 */
@Entity
@Table(name = "assetmaping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Assetmapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "goalid")
    private Integer goalid;

    @Column(name = "assetname")
    private String assetname;

    @Column(name = "assetid")
    private Integer assetid;
    
    @Column(name = "valuetomap")
    private Integer valuetomap;
    
    @Column(name = "assettype")
    private String assettype;
    
    public String getAssettype() {
		return assettype;
	}

	public void setAssettype(String assettype) {
		this.assettype = assettype;
	}


    public Integer getValuetomap() {
		return valuetomap;
	}

	public void setValuetomap(Integer valuetomap) {
		this.valuetomap = valuetomap;
	}

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

    public Assetmapping uid(Integer uid) {
        this.uid = uid;
        return this;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGoalid() {
        return goalid;
    }

    public Assetmapping goalid(Integer goalid) {
        this.goalid = goalid;
        return this;
    }

    public void setGoalid(Integer goalid) {
        this.goalid = goalid;
    }

    public String getAssetname() {
        return assetname;
    }

    public Assetmapping assetname(String assetname) {
        this.assetname = assetname;
        return this;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname;
    }

    public Integer getAssetid() {
        return assetid;
    }

    public Assetmapping assetid(Integer assetid) {
        this.assetid = assetid;
        return this;
    }

    public void setAssetid(Integer assetid) {
        this.assetid = assetid;
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
        Assetmapping assetmapping = (Assetmapping) o;
        if (assetmapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetmapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetmappingDomain{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", goalid=" + getGoalid() +
            ", assetname='" + getAssetname() + "'" +
            ", assetid=" + getAssetid() +
            ", valuetomap=" + getValuetomap() +
            "}";
    }
}
