package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.AssetmappingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Assetmapping and its DTO AssetmappingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AssetmappingMapper extends EntityMapper<AssetmappingDTO, Assetmapping> {



    default Assetmapping fromId(Long id) {
        if (id == null) {
            return null;
        }
        Assetmapping assetmapping = new Assetmapping();
        assetmapping.setId(id);
        return assetmapping;
    }
}
