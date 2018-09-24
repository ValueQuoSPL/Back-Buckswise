package com.valuequo.buckswise.service.mapper;

import org.mapstruct.Mapper;
import com.valuequo.buckswise.domain.GoalSet;
import com.valuequo.buckswise.service.dto.GoalSetDTO;

@Mapper(componentModel = "spring", uses = {})
public interface GoalSetMapper  extends EntityMapper<GoalSetDTO, GoalSet>{
	
	default GoalSet fromId(Long UID) {
        if (UID == null) {
            return null;
        }
        GoalSet goalset = new GoalSet();
        goalset.setUid(UID);
        return goalset;
    }

}
