package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.FamilyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Family and its DTO FamilyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FamilyMapper extends EntityMapper<FamilyDTO, Family> {

    default Family fromId(Long id) {
        if (id == null) {
            return null;
        }
        Family family = new Family();
        family.setId(id);
        return family;
    }
}
