package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.ChitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Chit and its DTO ChitDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChitMapper extends EntityMapper<ChitDTO, Chit> {



    default Chit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chit chit = new Chit();
        chit.setId(id);
        return chit;
    }
}
