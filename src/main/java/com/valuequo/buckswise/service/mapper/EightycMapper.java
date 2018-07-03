package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.EightycDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Eightyc and its DTO EightycDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EightycMapper extends EntityMapper<EightycDTO, Eightyc> {



    default Eightyc fromId(Long id) {
        if (id == null) {
            return null;
        }
        Eightyc eightyc = new Eightyc();
        eightyc.setId(id);
        return eightyc;
    }
}
