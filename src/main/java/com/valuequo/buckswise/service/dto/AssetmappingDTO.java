package com.valuequo.buckswise.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Assetmapping entity.
 */
public class AssetmappingDTO implements Serializable {

    private Long id;

    private Long uid;

    private Integer goalid;
    
    private Integer valuetomap;
    
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

	private String assetname;

    private Integer assetid;

    private String assetvalue;

    public String getAssetValue() {
        return assetvalue;
    }

    public void setAssetValue(String assetvalue) {
        this.assetvalue = assetvalue;
	}

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
            ", valuetomap=" + getValuetomap() +
            "assettype=" + getAssettype() +
            "}";
    }
}
