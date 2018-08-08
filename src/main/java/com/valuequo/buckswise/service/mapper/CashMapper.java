package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.CashDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cash and its DTO CashDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CashMapper extends EntityMapper<CashDTO, Cash> {



    default Cash fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cash cash = new Cash();
        cash.setId(id);
        return cash;
    }
}
