package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.RiskMedicalInsuranceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RiskMedicalInsurance and its DTO RiskMedicalInsuranceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskMedicalInsuranceMapper extends EntityMapper<RiskMedicalInsuranceDTO, RiskMedicalInsurance> {



    default RiskMedicalInsurance fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskMedicalInsurance riskMedicalInsurance = new RiskMedicalInsurance();
        riskMedicalInsurance.setId(id);
        return riskMedicalInsurance;
    }
}
