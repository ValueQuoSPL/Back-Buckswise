package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Assetmapping;
import com.valuequo.buckswise.repository.AssetmappingRepository;
import com.valuequo.buckswise.service.dto.AssetmappingDTO;
import com.valuequo.buckswise.service.mapper.AssetmappingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Assetmapping.
 */
@Service
@Transactional
public class AssetmappingService {

    private final Logger log = LoggerFactory.getLogger(AssetmappingService.class);

    private final AssetmappingRepository assetmappingRepository;

    private final AssetmappingMapper assetmappingMapper;

    public AssetmappingService(AssetmappingRepository assetmappingRepository, AssetmappingMapper assetmappingMapper) {
        this.assetmappingRepository = assetmappingRepository;
        this.assetmappingMapper = assetmappingMapper;
    }

    /**
     * Save a assetmapping.
     *
     * @param assetmappingDTO the entity to save
     * @return the persisted entity
     */
    public AssetmappingDTO save(AssetmappingDTO assetmappingDTO) {
        log.debug("Request to save Assetmapping : {}", assetmappingDTO);
        Assetmapping assetmapping = assetmappingMapper.toEntity(assetmappingDTO);
        assetmapping = assetmappingRepository.save(assetmapping);
        return assetmappingMapper.toDto(assetmapping);
    }

    /**
     * Get all the assetmappings.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AssetmappingDTO> findAll() {
        log.debug("Request to get all Assetmappings");
        return assetmappingRepository.findAll().stream()
            .map(assetmappingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one assetmapping by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AssetmappingDTO findOne(Long id) {
        log.debug("Request to get Assetmapping : {}", id);
        Assetmapping assetmapping = assetmappingRepository.findOne(id);
        return assetmappingMapper.toDto(assetmapping);
    }

    /**
     * Delete the assetmapping by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Assetmapping : {}", id);
        assetmappingRepository.delete(id);
    }
}
