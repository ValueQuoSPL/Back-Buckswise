package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Gross;
import com.valuequo.buckswise.repository.GrossRepository;
import com.valuequo.buckswise.service.dto.GrossDTO;
import com.valuequo.buckswise.service.mapper.GrossMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Gross.
 */
@Service
@Transactional
public class GrossService {

    private final Logger log = LoggerFactory.getLogger(GrossService.class);

    private final GrossRepository grossRepository;

    private final GrossMapper grossMapper;

    public GrossService(GrossRepository grossRepository, GrossMapper grossMapper) {
        this.grossRepository = grossRepository;
        this.grossMapper = grossMapper;
    }

    /**
     * Save a gross.
     *
     * @param grossDTO the entity to save
     * @return the persisted entity
     */
    public GrossDTO save(GrossDTO grossDTO) {
        log.debug("Request to save Gross : {}", grossDTO);
        Gross gross = grossMapper.toEntity(grossDTO);
        gross = grossRepository.save(gross);
        return grossMapper.toDto(gross);
    }

    /**
     * Get all the grosses.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GrossDTO> findAll() {
        log.debug("Request to get all Grosses");
        return grossRepository.findAll().stream()
            .map(grossMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one gross by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public GrossDTO findOne(Long id) {
        log.debug("Request to get Gross : {}", id);
        Gross gross = grossRepository.findOne(id);
        return grossMapper.toDto(gross);
    }

    /**
     * Delete the gross by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Gross : {}", id);
        grossRepository.delete(id);
    }
}
