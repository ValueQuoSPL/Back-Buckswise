package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.FutureOptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FutureOption and its DTO FutureOptionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FutureOptionMapper extends EntityMapper<FutureOptionDTO, FutureOption> {



    default FutureOption fromId(Long id) {
        if (id == null) {
            return null;
        }
        FutureOption futureOption = new FutureOption();
        futureOption.setId(id);
        return futureOption;
    }
}
