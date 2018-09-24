package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.GrossdeductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Grossdeduct and its DTO GrossdeductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrossdeductMapper extends EntityMapper<GrossdeductDTO, Grossdeduct> {



    default Grossdeduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        Grossdeduct grossdeduct = new Grossdeduct();
        grossdeduct.setId(id);
        return grossdeduct;
    }
}
