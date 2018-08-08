package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.MutualFundDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MutualFund and its DTO MutualFundDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MutualFundMapper extends EntityMapper<MutualFundDTO, MutualFund> {



    default MutualFund fromId(Long id) {
        if (id == null) {
            return null;
        }
        MutualFund mutualFund = new MutualFund();
        mutualFund.setId(id);
        return mutualFund;
    }
}
