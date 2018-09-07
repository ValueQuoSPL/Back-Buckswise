package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.RiskLifeInsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RiskLifeInsurance and its DTO RiskLifeInsuranceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskLifeInsuranceMapper extends EntityMapper<RiskLifeInsuranceDTO, RiskLifeInsurance> {



    default RiskLifeInsurance fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskLifeInsurance riskLifeInsurance = new RiskLifeInsurance();
        riskLifeInsurance.setId(id);
        return riskLifeInsurance;
    }
}
