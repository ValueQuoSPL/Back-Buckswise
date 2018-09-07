package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.RiskMedicalInsurance;
import com.valuequo.buckswise.repository.RiskMedicalInsuranceRepository;
import com.valuequo.buckswise.service.dto.RiskMedicalInsuranceDTO;
import com.valuequo.buckswise.service.mapper.RiskMedicalInsuranceMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing RiskMedicalInsurance.
 */
@Service
@Transactional
public class RiskMedicalInsuranceService {

    private final Logger log = LoggerFactory.getLogger(RiskMedicalInsuranceService.class);

    private final RiskMedicalInsuranceRepository riskMedicalInsuranceRepository;

    private final RiskMedicalInsuranceMapper riskMedicalInsuranceMapper;

    public RiskMedicalInsuranceService(RiskMedicalInsuranceRepository riskMedicalInsuranceRepository, RiskMedicalInsuranceMapper riskMedicalInsuranceMapper) {
        this.riskMedicalInsuranceRepository = riskMedicalInsuranceRepository;
        this.riskMedicalInsuranceMapper = riskMedicalInsuranceMapper;
    }

    /**
     * Save a riskMedicalInsurance.
     *
     * @param riskMedicalInsuranceDTO the entity to save
     * @return the persisted entity
     */
    public RiskMedicalInsuranceDTO save(RiskMedicalInsuranceDTO riskMedicalInsuranceDTO) {
        log.debug("Request to save RiskMedicalInsurance : {}", riskMedicalInsuranceDTO);
        RiskMedicalInsurance riskMedicalInsurance = riskMedicalInsuranceMapper.toEntity(riskMedicalInsuranceDTO);
        riskMedicalInsurance = riskMedicalInsuranceRepository.save(riskMedicalInsurance);
        return riskMedicalInsuranceMapper.toDto(riskMedicalInsurance);
    }

    /**
     * Get all the riskMedicalInsurances.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<RiskMedicalInsuranceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskMedicalInsurances");
        return riskMedicalInsuranceRepository.findAll(pageable)
            .map(riskMedicalInsuranceMapper::toDto);
    }

    /**
     * Get one riskMedicalInsurance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public RiskMedicalInsuranceDTO findOne(Long id) {
        log.debug("Request to get RiskMedicalInsurance : {}", id);
        RiskMedicalInsurance riskMedicalInsurance = riskMedicalInsuranceRepository.findOne(id);
        return riskMedicalInsuranceMapper.toDto(riskMedicalInsurance);
    }

    /**
     * Delete the riskMedicalInsurance by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskMedicalInsurance : {}", id);
        riskMedicalInsuranceRepository.delete(id);
    }

	public List<RiskMedicalInsurance> getdata(Long userid) {
		return riskMedicalInsuranceRepository.findByUserid(userid);
	}
}
