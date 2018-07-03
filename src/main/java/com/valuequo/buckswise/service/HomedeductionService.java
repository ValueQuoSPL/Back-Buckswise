package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Homededuction;
import com.valuequo.buckswise.repository.HomedeductionRepository;
import com.valuequo.buckswise.service.dto.HomedeductionDTO;
import com.valuequo.buckswise.service.mapper.HomedeductionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Homededuction.
 */
@Service
@Transactional
public class HomedeductionService {

    private final Logger log = LoggerFactory.getLogger(HomedeductionService.class);

    private final HomedeductionRepository homedeductionRepository;

    private final HomedeductionMapper homedeductionMapper;

    public HomedeductionService(HomedeductionRepository homedeductionRepository, HomedeductionMapper homedeductionMapper) {
        this.homedeductionRepository = homedeductionRepository;
        this.homedeductionMapper = homedeductionMapper;
    }

    /**
     * Save a homededuction.
     *
     * @param homedeductionDTO the entity to save
     * @return the persisted entity
     */
    public HomedeductionDTO save(HomedeductionDTO homedeductionDTO) {
        log.debug("Request to save Homededuction : {}", homedeductionDTO);
        Homededuction homededuction = homedeductionMapper.toEntity(homedeductionDTO);
        homededuction = homedeductionRepository.save(homededuction);
        return homedeductionMapper.toDto(homededuction);
    }

    /**
     * Get all the homedeductions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<HomedeductionDTO> findAll() {
        log.debug("Request to get all Homedeductions");
        return homedeductionRepository.findAll().stream()
            .map(homedeductionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one homededuction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public HomedeductionDTO findOne(Long id) {
        log.debug("Request to get Homededuction : {}", id);
        Homededuction homededuction = homedeductionRepository.findOne(id);
        return homedeductionMapper.toDto(homededuction);
    }

    /**
     * Delete the homededuction by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Homededuction : {}", id);
        homedeductionRepository.delete(id);
    }
}
