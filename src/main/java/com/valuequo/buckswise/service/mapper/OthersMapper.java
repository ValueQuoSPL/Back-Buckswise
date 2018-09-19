package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.OthersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Others and its DTO OthersDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OthersMapper extends EntityMapper<OthersDTO, Others> {



    default Others fromId(Long id) {
        if (id == null) {
            return null;
        }
        Others others = new Others();
        others.setId(id);
        return others;
    }
}
