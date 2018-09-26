package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Assetmapping entity.
 */
public class AssetmappingDTO implements Serializable {

    private Long id;

    private Integer uid;

    private Integer goalid;

    private String assetname;

    private Integer assetid;

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

    public Integer getGoalid() {
        return goalid;
    }

    public void setGoalid(Integer goalid) {
        this.goalid = goalid;
    }

    public String getAssetname() {
        return assetname;
    }

    public void setAssetname(String assetname) {
        this.assetname = assetname;
    }

    public Integer getAssetid() {
        return assetid;
    }

    public void setAssetid(Integer assetid) {
        this.assetid = assetid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssetmappingDTO assetmappingDTO = (AssetmappingDTO) o;
        if(assetmappingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetmappingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetmappingDTO{" +
            "id=" + getId() +
            ", uid=" + getUid() +
            ", goalid=" + getGoalid() +
            ", assetname='" + getAssetname() + "'" +
            ", assetid=" + getAssetid() +
            "}";
    }
}
