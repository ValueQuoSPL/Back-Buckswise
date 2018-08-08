package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.AtlernateInvestmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AtlernateInvestment and its DTO AtlernateInvestmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AtlernateInvestmentMapper extends EntityMapper<AtlernateInvestmentDTO, AtlernateInvestment> {



    default AtlernateInvestment fromId(Long id) {
        if (id == null) {
            return null;
        }
        AtlernateInvestment atlernateInvestment = new AtlernateInvestment();
        atlernateInvestment.setId(id);
        return atlernateInvestment;
    }
}
