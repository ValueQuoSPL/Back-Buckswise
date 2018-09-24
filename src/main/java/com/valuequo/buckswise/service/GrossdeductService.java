package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Grossdeduct;
import com.valuequo.buckswise.repository.GrossdeductRepository;
import com.valuequo.buckswise.service.dto.GrossdeductDTO;
import com.valuequo.buckswise.service.mapper.GrossdeductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Grossdeduct.
 */
@Service
@Transactional
public class GrossdeductService {

    private final Logger log = LoggerFactory.getLogger(GrossdeductService.class);

    private final GrossdeductRepository grossdeductRepository;

    private final GrossdeductMapper grossdeductMapper;

    public GrossdeductService(GrossdeductRepository grossdeductRepository, GrossdeductMapper grossdeductMapper) {
        this.grossdeductRepository = grossdeductRepository;
        this.grossdeductMapper = grossdeductMapper;
    }

    /**
     * Save a grossdeduct.
     *
     * @param grossdeductDTO the entity to save
     * @return the persisted entity
     */
    public GrossdeductDTO save(GrossdeductDTO grossdeductDTO) {
        log.debug("Request to save Grossdeduct : {}", grossdeductDTO);
        Grossdeduct grossdeduct = grossdeductMapper.toEntity(grossdeductDTO);
        grossdeduct = grossdeductRepository.save(grossdeduct);
        return grossdeductMapper.toDto(grossdeduct);
    }

    /**
     * Get all the grossdeducts.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<GrossdeductDTO> findAll() {
        log.debug("Request to get all Grossdeducts");
        return grossdeductRepository.findAll().stream()
            .map(grossdeductMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one grossdeduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public GrossdeductDTO findOne(Long uid) {
        log.debug("Request to get Grossdeduct : {}", uid);
        Grossdeduct grossdeduct = grossdeductRepository.findOne(uid);
        return grossdeductMapper.toDto(grossdeduct);
    }

    /**
     * Delete the grossdeduct by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Grossdeduct : {}", id);
        grossdeductRepository.delete(id);
    }
}
