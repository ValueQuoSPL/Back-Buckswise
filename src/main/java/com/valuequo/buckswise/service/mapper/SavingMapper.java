package com.valuequo.buckswise.service.mapper;
import com.valuequo.buckswise.domain.*;
import org.mapstruct.Mapper;
import com.valuequo.buckswise.service.dto.SavingSchemeDTO;
	
	@Mapper(componentModel = "spring", uses = {})
	public interface SavingMapper extends EntityMapper<SavingSchemeDTO, SavingScheme> {

	    default SavingScheme fromId(Long id) {
	        if (id == null) {
	            return null;
	        }
	        SavingScheme savingScheme = new SavingScheme();
	        savingScheme.setId(id);
	        return savingScheme;
	    }
	}

