package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.HomedeductionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Homededuction and its DTO HomedeductionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HomedeductionMapper extends EntityMapper<HomedeductionDTO, Homededuction> {



    default Homededuction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Homededuction homededuction = new Homededuction();
        homededuction.setId(id);
        return homededuction;
    }
}
