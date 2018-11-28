package com.valuequo.buckswise.service.mapper;

import java.util.List;

import com.valuequo.buckswise.domain.Contactus;
import com.valuequo.buckswise.service.dto.AppointmentDTO;
import com.valuequo.buckswise.service.dto.AssetmappingDTO;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper <D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List <E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}
