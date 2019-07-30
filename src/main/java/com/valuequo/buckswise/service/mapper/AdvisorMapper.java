package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.AdvisorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Advisor and its DTO AdvisorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AdvisorMapper extends EntityMapper<AdvisorDTO, Advisor> {



    default Advisor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Advisor advisor = new Advisor();
        advisor.setId(id);
        return advisor;
    }
}
