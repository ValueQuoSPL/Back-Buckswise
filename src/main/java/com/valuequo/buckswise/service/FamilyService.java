package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Family;
import com.valuequo.buckswise.repository.FamilyRepository;
import com.valuequo.buckswise.service.dto.FamilyDTO;
import com.valuequo.buckswise.service.mapper.FamilyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Family.
 */
@Service
@Transactional
public class FamilyService {

    private final Logger log = LoggerFactory.getLogger(FamilyService.class);

    private final FamilyRepository familyRepository;

    private final FamilyMapper familyMapper;

    public FamilyService(FamilyRepository familyRepository, FamilyMapper familyMapper) {
        this.familyRepository = familyRepository;
        this.familyMapper = familyMapper;
    }

    /**
     * Save a family.
     *
     * @param familyDTO the entity to save
     * @return the persisted entity
     */
    public FamilyDTO save(FamilyDTO familyDTO) {
        log.debug("Request to save Family : {}", familyDTO);
        Family family = familyMapper.toEntity(familyDTO);
        family = familyRepository.save(family);
        return familyMapper.toDto(family);
    }

    /**
     * Get all the families.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FamilyDTO> findAll() {
        log.debug("Request to get all Families");
        return familyRepository.findAll().stream()
            .map(familyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one family by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FamilyDTO findOne(Long id) {
        log.debug("Request to get Family : {}", id);
        Family family = familyRepository.findOne(id);
        return familyMapper.toDto(family);
    }

    /**
     * Delete the family by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Family : {}", id);
        familyRepository.delete(id);
    }
}
