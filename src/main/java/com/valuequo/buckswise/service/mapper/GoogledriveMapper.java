package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.GoogledriveDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface GoogledriveMapper extends EntityMapper<GoogledriveDTO, Googledrive> {

    default Googledrive fromId(Long id) {
        if (id == null) {
            return null;
        }
        Googledrive googledrive = new Googledrive();
        googledrive.setId(id);
        return googledrive;
    }
}