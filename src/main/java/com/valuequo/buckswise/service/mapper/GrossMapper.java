package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.GrossDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Gross and its DTO GrossDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrossMapper extends EntityMapper<GrossDTO, Gross> {



    default Gross fromId(Long id) {
        if (id == null) {
            return null;
        }
        Gross gross = new Gross();
        gross.setId(id);
        return gross;
    }
}
