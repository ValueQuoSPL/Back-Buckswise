package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.PromocodeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Promocode and its DTO PromocodeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PromocodeMapper extends EntityMapper<PromocodeDTO, Promocode> {



    default Promocode fromId(Long id) {
        if (id == null) {
            return null;
        }
        Promocode promocode = new Promocode();
        promocode.setId(id);
        return promocode;
    }
}
