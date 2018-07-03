package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Other;
import com.valuequo.buckswise.repository.OtherRepository;
import com.valuequo.buckswise.service.dto.OtherDTO;
import com.valuequo.buckswise.service.mapper.OtherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Other.
 */
@Service
@Transactional
public class OtherService {

    private final Logger log = LoggerFactory.getLogger(OtherService.class);

    private final OtherRepository otherRepository;

    private final OtherMapper otherMapper;

    public OtherService(OtherRepository otherRepository, OtherMapper otherMapper) {
        this.otherRepository = otherRepository;
        this.otherMapper = otherMapper;
    }

    /**
     * Save a other.
     *
     * @param otherDTO the entity to save
     * @return the persisted entity
     */
    public OtherDTO save(OtherDTO otherDTO) {
        log.debug("Request to save Other : {}", otherDTO);
        Other other = otherMapper.toEntity(otherDTO);
        other = otherRepository.save(other);
        return otherMapper.toDto(other);
    }

    /**
     * Get all the others.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<OtherDTO> findAll() {
        log.debug("Request to get all Others");
        return otherRepository.findAll().stream()
            .map(otherMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one other by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public OtherDTO findOne(Long id) {
        log.debug("Request to get Other : {}", id);
        Other other = otherRepository.findOne(id);
        return otherMapper.toDto(other);
    }

    /**
     * Delete the other by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Other : {}", id);
        otherRepository.delete(id);
    }
}
