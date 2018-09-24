package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Eightyd;
import com.valuequo.buckswise.repository.EightydRepository;
import com.valuequo.buckswise.service.dto.EightydDTO;
import com.valuequo.buckswise.service.mapper.EightydMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Eightyd.
 */
@Service
@Transactional
public class EightydService {

    private final Logger log = LoggerFactory.getLogger(EightydService.class);

    private final EightydRepository eightydRepository;

    private final EightydMapper eightydMapper;

    public EightydService(EightydRepository eightydRepository, EightydMapper eightydMapper) {
        this.eightydRepository = eightydRepository;
        this.eightydMapper = eightydMapper;
    }

    /**
     * Save a eightyd.
     *
     * @param eightydDTO the entity to save
     * @return the persisted entity
     */
    public EightydDTO save(EightydDTO eightydDTO) {
        log.debug("Request to save Eightyd : {}", eightydDTO);
        Eightyd eightyd = eightydMapper.toEntity(eightydDTO);
        eightyd = eightydRepository.save(eightyd);
        return eightydMapper.toDto(eightyd);
    }

    /**
     * Get all the eightyds.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EightydDTO> findAll() {
        log.debug("Request to get all Eightyds");
        return eightydRepository.findAll().stream()
            .map(eightydMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one eightyd by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EightydDTO findOne(Long uid) {
        log.debug("Request to get Eightyd : {}", uid);
        Eightyd eightyd = eightydRepository.findOne(uid);
        return eightydMapper.toDto(eightyd);
    }

    /**
     * Delete the eightyd by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Eightyd : {}", id);
        eightydRepository.delete(id);
    }
}
