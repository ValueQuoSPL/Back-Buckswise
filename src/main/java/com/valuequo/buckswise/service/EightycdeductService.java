package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Eightycdeduct;
import com.valuequo.buckswise.repository.EightycdeductRepository;
import com.valuequo.buckswise.service.dto.EightycdeductDTO;
import com.valuequo.buckswise.service.mapper.EightycdeductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Eightycdeduct.
 */
@Service
@Transactional
public class EightycdeductService {

    private final Logger log = LoggerFactory.getLogger(EightycdeductService.class);

    private final EightycdeductRepository eightycdeductRepository;

    private final EightycdeductMapper eightycdeductMapper;

    public EightycdeductService(EightycdeductRepository eightycdeductRepository, EightycdeductMapper eightycdeductMapper) {
        this.eightycdeductRepository = eightycdeductRepository;
        this.eightycdeductMapper = eightycdeductMapper;
    }

    /**
     * Save a eightycdeduct.
     *
     * @param eightycdeductDTO the entity to save
     * @return the persisted entity
     */
    public EightycdeductDTO save(EightycdeductDTO eightycdeductDTO) {
        log.debug("Request to save Eightycdeduct : {}", eightycdeductDTO);
        Eightycdeduct eightycdeduct = eightycdeductMapper.toEntity(eightycdeductDTO);
        eightycdeduct = eightycdeductRepository.save(eightycdeduct);
        return eightycdeductMapper.toDto(eightycdeduct);
    }

    /**
     * Get all the eightycdeducts.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EightycdeductDTO> findAll() {
        log.debug("Request to get all Eightycdeducts");
        return eightycdeductRepository.findAll().stream()
            .map(eightycdeductMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one eightycdeduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public EightycdeductDTO findOne(Long uid) {
        log.debug("Request to get Eightycdeduct : {}", uid);
        Eightycdeduct eightycdeduct = eightycdeductRepository.findOne(uid);
        return eightycdeductMapper.toDto(eightycdeduct);
    }

    /**
     * Delete the eightycdeduct by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Eightycdeduct : {}", id);
        eightycdeductRepository.delete(id);
    }
}
