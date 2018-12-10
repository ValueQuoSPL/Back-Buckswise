package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Property;
import com.valuequo.buckswise.repository.PropertyRepository;
import com.valuequo.buckswise.service.dto.PropertyDTO;
import com.valuequo.buckswise.service.mapper.PropertyMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Property.
 */
@Service
@Transactional
public class PropertyService {

    private final Logger log = LoggerFactory.getLogger(PropertyService.class);

    private final PropertyRepository propertyRepository;

    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository propertyRepository, PropertyMapper propertyMapper) {
        this.propertyRepository = propertyRepository;
        this.propertyMapper = propertyMapper;
    }

    /**
     * Save a property.
     *
     * @param propertyDTO the entity to save
     * @return the persisted entity
     */
    public PropertyDTO save(PropertyDTO propertyDTO) {
        log.debug("Request to save Property : {}", propertyDTO);
        Property property = propertyMapper.toEntity(propertyDTO);
        property.setAvailable(property.getCurrent_m_value());
        property = propertyRepository.save(property);
        return propertyMapper.toDto(property);
    }

    /**
     * Get all the properties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PropertyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Properties");
        return propertyRepository.findAll(pageable)
            .map(propertyMapper::toDto);
    }

    /**
     * Get one property by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public PropertyDTO findOne(Long id) {
        log.debug("Request to get Property : {}", id);
        Property property = propertyRepository.findOne(id);
        return propertyMapper.toDto(property);
    }

    /**
     * Delete the property by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Property : {}", id);
        propertyRepository.delete(id);
    }

	public List<Property> getPropertie(Long userid) {
		return propertyRepository.findByUserid(userid);
	}

	public void updateAvailable(Long id, String avail) {
        Property property = propertyRepository.findById(id);
        property.setAvailable(avail);
        propertyRepository.save(property);
	}
}
