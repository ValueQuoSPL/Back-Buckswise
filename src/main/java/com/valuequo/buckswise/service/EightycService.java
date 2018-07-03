package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Eightyc;
import com.valuequo.buckswise.repository.EightycRepository;
import com.valuequo.buckswise.service.dto.EightycDTO;
import com.valuequo.buckswise.service.mapper.EightycMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Eightyc.
 */
@Service
@Transactional
public class EightycService {

    private final Logger log = LoggerFactory.getLogger(EightycService.class);

    private final EightycRepository eightycRepository;

    private final EightycMapper eightycMapper;

    public EightycService(EightycRepository eightycRepository, EightycMapper eightycMapper) {
        this.eightycRepository = eightycRepository;
        this.eightycMapper = eightycMapper;
    }

    /**
     * Save a eightyc.
     *
     * @param eightycDTO the entity to save
     * @return the persisted entity
     */
    public EightycDTO save(EightycDTO eightycDTO) {
        log.debug("Request to save Eightyc : {}", eightycDTO);
        Eightyc eightyc = eightycMapper.toEntity(eightycDTO);
        eightyc = eightycRepository.save(eightyc);
        return eightycMapper.toDto(eightyc);
    }

    /**
     * Get all the eightycs.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EightycDTO> findAll() {
        log.debug("Request to get all Eightycs");
        return eightycRepository.findAll().stream()
            .map(eightycMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one eightyc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EightycDTO findOne(Long id) {
        log.debug("Request to get Eightyc : {}", id);
        Eightyc eightyc = eightycRepository.findOne(id);
        return eightycMapper.toDto(eightyc);
    }

    /**
     * Delete the eightyc by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Eightyc : {}", id);
        eightycRepository.delete(id);
    }
}
