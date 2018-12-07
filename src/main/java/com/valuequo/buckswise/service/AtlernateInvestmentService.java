package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.AtlernateInvestment;
import com.valuequo.buckswise.repository.AtlernateInvestmentRepository;
import com.valuequo.buckswise.service.dto.AtlernateInvestmentDTO;
import com.valuequo.buckswise.service.mapper.AtlernateInvestmentMapper;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing AtlernateInvestment.
 */
@Service
@Transactional
public class AtlernateInvestmentService {

    private final Logger log = LoggerFactory.getLogger(AtlernateInvestmentService.class);

    private final AtlernateInvestmentRepository atlernateInvestmentRepository;

    private final AtlernateInvestmentMapper atlernateInvestmentMapper;

    public AtlernateInvestmentService(AtlernateInvestmentRepository atlernateInvestmentRepository, AtlernateInvestmentMapper atlernateInvestmentMapper) {
        this.atlernateInvestmentRepository = atlernateInvestmentRepository;
        this.atlernateInvestmentMapper = atlernateInvestmentMapper;
    }

    /**
     * Save a atlernateInvestment.
     *
     * @param atlernateInvestmentDTO the entity to save
     * @return the persisted entity
     */
    public AtlernateInvestmentDTO save(AtlernateInvestmentDTO atlernateInvestmentDTO) {
        log.debug("Request to save AtlernateInvestment : {}", atlernateInvestmentDTO);
        AtlernateInvestment atlernateInvestment = atlernateInvestmentMapper.toEntity(atlernateInvestmentDTO);
        atlernateInvestment.setAvailable(atlernateInvestment.getMarket_value());
        atlernateInvestment = atlernateInvestmentRepository.save(atlernateInvestment);
        return atlernateInvestmentMapper.toDto(atlernateInvestment);
    }

    /**
     * Get all the atlernateInvestments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<AtlernateInvestmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AtlernateInvestments");
        return atlernateInvestmentRepository.findAll(pageable)
            .map(atlernateInvestmentMapper::toDto);
    }

    /**
     * Get one atlernateInvestment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AtlernateInvestmentDTO findOne(Long id) {
        log.debug("Request to get AtlernateInvestment : {}", id);
        AtlernateInvestment atlernateInvestment = atlernateInvestmentRepository.findOne(id);
        return atlernateInvestmentMapper.toDto(atlernateInvestment);
    }

    /**
     * Delete the atlernateInvestment by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AtlernateInvestment : {}", id);
        atlernateInvestmentRepository.delete(id);
    }

	public List<AtlernateInvestment> getAInvestment(Long userid) {
		// TODO Auto-generated method stub
		return atlernateInvestmentRepository.findByUserId(userid);
	}

    /**
     * Author - Pratik
     * @param id
     * @param avail
     */
	public void updateAvailable(Long id, String avail) {
        AtlernateInvestment alt = atlernateInvestmentRepository.findById(id);
        alt.setAvailable(avail);
        atlernateInvestmentRepository.save(alt);
	}
}
