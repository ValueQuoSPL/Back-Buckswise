package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.OtherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Other and its DTO OtherDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OtherMapper extends EntityMapper<OtherDTO, Other> {



    default Other fromId(Long id) {
        if (id == null) {
            return null;
        }
        Other other = new Other();
        other.setId(id);
        return other;
    }
}
