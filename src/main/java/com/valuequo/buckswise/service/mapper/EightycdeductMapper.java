package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.EightycdeductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Eightycdeduct and its DTO EightycdeductDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EightycdeductMapper extends EntityMapper<EightycdeductDTO, Eightycdeduct> {



    default Eightycdeduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        Eightycdeduct eightycdeduct = new Eightycdeduct();
        eightycdeduct.setId(id);
        return eightycdeduct;
    }
}
