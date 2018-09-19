package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Promocode;
import com.valuequo.buckswise.repository.PromocodeRepository;
import com.valuequo.buckswise.service.dto.PromocodeDTO;
import com.valuequo.buckswise.service.mapper.PromocodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Promocode.
 */
@Service
@Transactional
public class PromocodeService {

    private final Logger log = LoggerFactory.getLogger(PromocodeService.class);

    private final PromocodeRepository promocodeRepository;

    private final PromocodeMapper promocodeMapper;

    public PromocodeService(PromocodeRepository promocodeRepository, PromocodeMapper promocodeMapper) {
        this.promocodeRepository = promocodeRepository;
        this.promocodeMapper = promocodeMapper;
    }

    /**
     * Save a promocode.
     *
     * @param promocodeDTO the entity to save
     * @return the persisted entity
     */
    public PromocodeDTO save(PromocodeDTO promocodeDTO) {
        log.debug("Request to save Promocode : {}", promocodeDTO);
        Promocode promocode = promocodeMapper.toEntity(promocodeDTO);
        promocode = promocodeRepository.save(promocode);
        return promocodeMapper.toDto(promocode);
    }

    /**
     * Get all the promocodes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PromocodeDTO> findAll() {
        log.debug("Request to get all Promocodes");
        return promocodeRepository.findAll().stream()
            .map(promocodeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one promocode by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PromocodeDTO> findOne(Long id) {
        log.debug("Request to get Promocode : {}", id);
        return promocodeRepository.findById(id);
    }

    /**
     * Delete the promocode by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Promocode : {}", id);
        promocodeRepository.deleteById(id);
    }
}
