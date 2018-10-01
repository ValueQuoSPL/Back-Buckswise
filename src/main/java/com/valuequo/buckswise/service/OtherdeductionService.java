package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Eightycdeduct;
import com.valuequo.buckswise.domain.Otherdeduction;
import com.valuequo.buckswise.repository.OtherdeductionRepository;
import com.valuequo.buckswise.service.dto.EightycdeductDTO;
import com.valuequo.buckswise.service.dto.OtherdeductionDTO;
import com.valuequo.buckswise.service.mapper.OtherdeductionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Otherdeduction.
 */
@Service
@Transactional
public class OtherdeductionService {

    private final Logger log = LoggerFactory.getLogger(OtherdeductionService.class);

    private final OtherdeductionRepository otherdeductionRepository;

    private final OtherdeductionMapper otherdeductionMapper;

    public OtherdeductionService(OtherdeductionRepository otherdeductionRepository, OtherdeductionMapper otherdeductionMapper) {
        this.otherdeductionRepository = otherdeductionRepository;
        this.otherdeductionMapper = otherdeductionMapper;
    }

    /**
     * Save a otherdeduction.
     *
     * @param otherdeductionDTO the entity to save
     * @return the persisted entity
     */
    public OtherdeductionDTO save(OtherdeductionDTO otherdeductionDTO) {
        log.debug("Request to save Otherdeduction : {}", otherdeductionDTO);
        Otherdeduction otherdeduction = otherdeductionMapper.toEntity(otherdeductionDTO);
        otherdeduction = otherdeductionRepository.save(otherdeduction);
        return otherdeductionMapper.toDto(otherdeduction);
    }

    /**
     * Get all the otherdeductions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<OtherdeductionDTO> findAll() {
        log.debug("Request to get all Otherdeductions");
        return otherdeductionRepository.findAll().stream()
            .map(otherdeductionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one otherdeduction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public List<OtherdeductionDTO> findOne(int uid) {
        log.debug("Request to get Otherdeduction : {}", uid);
        List<Otherdeduction> otherdeduction = otherdeductionRepository.findByUid(uid);
        return otherdeductionMapper.toDto(otherdeduction);
    }
    

    /**
     * Delete the otherdeduction by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Otherdeduction : {}", id);
        otherdeductionRepository.delete(id);
    }
}
