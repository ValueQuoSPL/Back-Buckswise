package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.OtherdeductionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Otherdeduction and its DTO OtherdeductionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OtherdeductionMapper extends EntityMapper<OtherdeductionDTO, Otherdeduction> {



    default Otherdeduction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Otherdeduction otherdeduction = new Otherdeduction();
        otherdeduction.setId(id);
        return otherdeduction;
    }
}
