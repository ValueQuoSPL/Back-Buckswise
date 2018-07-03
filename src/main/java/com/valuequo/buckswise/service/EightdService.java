package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Eightd;
import com.valuequo.buckswise.repository.EightdRepository;
import com.valuequo.buckswise.service.dto.EightdDTO;
import com.valuequo.buckswise.service.mapper.EightdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Eightd.
 */
@Service
@Transactional
public class EightdService {

    private final Logger log = LoggerFactory.getLogger(EightdService.class);

    private final EightdRepository eightdRepository;

    private final EightdMapper eightdMapper;

    public EightdService(EightdRepository eightdRepository, EightdMapper eightdMapper) {
        this.eightdRepository = eightdRepository;
        this.eightdMapper = eightdMapper;
    }

    /**
     * Save a eightd.
     *
     * @param eightdDTO the entity to save
     * @return the persisted entity
     */
    public EightdDTO save(EightdDTO eightdDTO) {
        log.debug("Request to save Eightd : {}", eightdDTO);
        Eightd eightd = eightdMapper.toEntity(eightdDTO);
        eightd = eightdRepository.save(eightd);
        return eightdMapper.toDto(eightd);
    }

    /**
     * Get all the eightds.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EightdDTO> findAll() {
        log.debug("Request to get all Eightds");
        return eightdRepository.findAll().stream()
            .map(eightdMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one eightd by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EightdDTO findOne(Long id) {
        log.debug("Request to get Eightd : {}", id);
        Eightd eightd = eightdRepository.findOne(id);
        return eightdMapper.toDto(eightd);
    }

    /**
     * Delete the eightd by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Eightd : {}", id);
        eightdRepository.delete(id);
    }
}
