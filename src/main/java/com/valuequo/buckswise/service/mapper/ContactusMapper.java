package com.valuequo.buckswise.service.mapper;

import com.valuequo.buckswise.domain.*;
import com.valuequo.buckswise.service.dto.ContactusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contactus and its DTO ContactusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactusMapper extends EntityMapper<ContactusDTO, Contactus> {



    default Contactus fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contactus contactus = new Contactus();
        contactus.setId(id);
        return contactus;
    }
}
