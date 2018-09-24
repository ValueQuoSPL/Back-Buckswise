package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.FamilyprofileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Familyprofile and its DTO FamilyprofileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FamilyprofileMapper extends EntityMapper<FamilyprofileDTO, Familyprofile> {

    default Familyprofile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Familyprofile familyprofile = new Familyprofile();
        familyprofile.setId(id);
        return familyprofile;
    }
}
