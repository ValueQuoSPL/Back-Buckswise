package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.HomeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Home and its DTO HomeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HomeMapper extends EntityMapper<HomeDTO, Home> {



    default Home fromId(Long id) {
        if (id == null) {
            return null;
        }
        Home home = new Home();
        home.setId(id);
        return home;
    }
}
