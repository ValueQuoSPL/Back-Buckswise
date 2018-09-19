package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.UserplanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Userplan and its DTO UserplanDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserplanMapper extends EntityMapper<UserplanDTO, Userplan> {



    default Userplan fromId(Long id) {
        if (id == null) {
            return null;
        }
        Userplan userplan = new Userplan();
        userplan.setId(id);
        return userplan;
    }
}
