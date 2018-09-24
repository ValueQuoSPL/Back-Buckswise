package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.RiskLifeInsurance;
import com.valuequo.buckswise.repository.RiskLifeInsuranceRepository;
import com.valuequo.buckswise.service.dto.RiskLifeInsuranceDTO;
import com.valuequo.buckswise.service.mapper.RiskLifeInsuranceMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing RiskLifeInsurance.
 */
@Service
@Transactional
public class RiskLifeInsuranceService {

    private final Logger log = LoggerFactory.getLogger(RiskLifeInsuranceService.class);

    private final RiskLifeInsuranceRepository riskLifeInsuranceRepository;

    private final RiskLifeInsuranceMapper riskLifeInsuranceMapper;

    public RiskLifeInsuranceService(RiskLifeInsuranceRepository riskLifeInsuranceRepository, RiskLifeInsuranceMapper riskLifeInsuranceMapper) {
        this.riskLifeInsuranceRepository = riskLifeInsuranceRepository;
        this.riskLifeInsuranceMapper = riskLifeInsuranceMapper;
    }

    /**
     * Save a riskLifeInsurance.
     *
     * @param riskLifeInsuranceDTO the entity to save
     * @return the persisted entity
     */
    public RiskLifeInsuranceDTO save(RiskLifeInsuranceDTO riskLifeInsuranceDTO) {
        log.debug("Request to save RiskLifeInsurance : {}", riskLifeInsuranceDTO);
        RiskLifeInsurance riskLifeInsurance = riskLifeInsuranceMapper.toEntity(riskLifeInsuranceDTO);
        riskLifeInsurance = riskLifeInsuranceRepository.save(riskLifeInsurance);
        return riskLifeInsuranceMapper.toDto(riskLifeInsurance);
    }

    /**
     * Get all the riskLifeInsurances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<RiskLifeInsuranceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskLifeInsurances");
        return riskLifeInsuranceRepository.findAll(pageable)
            .map(riskLifeInsuranceMapper::toDto);
    }

    /**
     * Get one riskLifeInsurance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public RiskLifeInsuranceDTO findOne(Long id) {
        log.debug("Request to get RiskLifeInsurance : {}", id);
        RiskLifeInsurance riskLifeInsurance = riskLifeInsuranceRepository.findOne(id);
        return riskLifeInsuranceMapper.toDto(riskLifeInsurance);
    }

    /**
     * Delete the riskLifeInsurance by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskLifeInsurance : {}", id);
        riskLifeInsuranceRepository.delete(id);
    }

	public List<RiskLifeInsurance> details(Long userid) {
		return riskLifeInsuranceRepository.findByUserid(userid);
	}
}
