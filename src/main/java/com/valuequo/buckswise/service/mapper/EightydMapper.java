package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.EightydDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Eightyd and its DTO EightydDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EightydMapper extends EntityMapper<EightydDTO, Eightyd> {



    default Eightyd fromId(Long id) {
        if (id == null) {
            return null;
        }
        Eightyd eightyd = new Eightyd();
        eightyd.setId(id);
        return eightyd;
    }
}
