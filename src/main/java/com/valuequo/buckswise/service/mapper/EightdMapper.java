package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.EightdDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Eightd and its DTO EightdDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EightdMapper extends EntityMapper<EightdDTO, Eightd> {



    default Eightd fromId(Long id) {
        if (id == null) {
            return null;
        }
        Eightd eightd = new Eightd();
        eightd.setId(id);
        return eightd;
    }
}
