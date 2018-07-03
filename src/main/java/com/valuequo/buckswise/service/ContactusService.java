package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Contactus;
import com.valuequo.buckswise.repository.ContactusRepository;
import com.valuequo.buckswise.service.dto.ContactusDTO;
import com.valuequo.buckswise.service.mapper.ContactusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Contactus.
 */
@Service
@Transactional
public class ContactusService {

    private final Logger log = LoggerFactory.getLogger(ContactusService.class);

    private final ContactusRepository contactusRepository;

    private final ContactusMapper contactusMapper;

    public ContactusService(ContactusRepository contactusRepository, ContactusMapper contactusMapper) {
        this.contactusRepository = contactusRepository;
        this.contactusMapper = contactusMapper;
    }

    /**
     * Save a contactus.
     *
     * @param contactusDTO the entity to save
     * @return the persisted entity
     */
    public ContactusDTO save(ContactusDTO contactusDTO) {
        log.debug("Request to save Contactus : {}", contactusDTO);
        Contactus contactus = contactusMapper.toEntity(contactusDTO);
        contactus = contactusRepository.save(contactus);
        return contactusMapper.toDto(contactus);
    }

    /**
     * Get all the contactuses.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ContactusDTO> findAll() {
        log.debug("Request to get all Contactuses");
        return contactusRepository.findAll().stream()
            .map(contactusMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one contactus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ContactusDTO findOne(Long id) {
        log.debug("Request to get Contactus : {}", id);
        Contactus contactus = contactusRepository.findOne(id);
        return contactusMapper.toDto(contactus);
    }

    /**
     * Delete the contactus by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Contactus : {}", id);
        contactusRepository.delete(id);
    }
}
