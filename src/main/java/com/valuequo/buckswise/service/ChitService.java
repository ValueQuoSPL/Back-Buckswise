package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.repository.ChitRepository;
import com.valuequo.buckswise.service.dto.ChitDTO;
import com.valuequo.buckswise.service.mapper.ChitMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Chit.
 */
@Service
@Transactional
public class ChitService {

    private final Logger log = LoggerFactory.getLogger(ChitService.class);

    private final ChitRepository chitRepository;

    private final ChitMapper chitMapper;

    public ChitService(ChitRepository chitRepository, ChitMapper chitMapper) {
        this.chitRepository = chitRepository;
        this.chitMapper = chitMapper;
    }

    /**
     * Save a chit.
     *
     * @param chitDTO the entity to save
     * @return the persisted entity
     */
    public ChitDTO save(ChitDTO chitDTO) {
        log.debug("Request to save Chit : {}", chitDTO);
        Chit chit = chitMapper.toEntity(chitDTO);
        chit.setAvailable(chit.getCurrent_value());
        chit = chitRepository.save(chit);
        return chitMapper.toDto(chit);
    }

    /**
     * Get all the chits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ChitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Chits");
        return chitRepository.findAll(pageable)
            .map(chitMapper::toDto);
    }

    /**
     * Get one chit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ChitDTO findOne(Long id) {
        log.debug("Request to get Chit : {}", id);
        Chit chit = chitRepository.findOne(id);
        return chitMapper.toDto(chit);
    }

    /**
     * Delete the chit by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Chit : {}", id);
        chitRepository.delete(id);
    }

	public List<Chit> getAllChit(Long userid) {
		return chitRepository.findByUserid(userid);
    }

    /**
     * Author - Pratik
     * @param id
     * @param avail
     */
    public void updateAvailable(Long id, String avail) {
        Chit chit = chitRepository.findById(id);
        chit.setAvailable(avail);
        chitRepository.save(chit);
	}

}
