package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MutualFund.
 */
@Service
@Transactional
public class MutualFundService {

    private final Logger log = LoggerFactory.getLogger(MutualFundService.class);

    private final MutualFundRepository mutualFundRepository;

    private final MutualFundMapper mutualFundMapper;

    public MutualFundService(MutualFundRepository mutualFundRepository, MutualFundMapper mutualFundMapper) {
        this.mutualFundRepository = mutualFundRepository;
        this.mutualFundMapper = mutualFundMapper;
    }

    /**
     * Save a mutualFund.
     *
     * @param mutualFundDTO the entity to save
     * @return the persisted entity
     */
    public MutualFundDTO save(MutualFundDTO mutualFundDTO) {
        log.debug("Request to save MutualFund : {}", mutualFundDTO);
        MutualFund mutualFund = mutualFundMapper.toEntity(mutualFundDTO);
        mutualFund = mutualFundRepository.save(mutualFund);
        return mutualFundMapper.toDto(mutualFund);
    }

    /**
     * Get all the mutualFunds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MutualFundDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MutualFunds");
        return mutualFundRepository.findAll(pageable)
            .map(mutualFundMapper::toDto);
    }

    /**
     * Get one mutualFund by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public MutualFundDTO findOne(Long id) {
        log.debug("Request to get MutualFund : {}", id);
        MutualFund mutualFund = mutualFundRepository.findOne(id);
        return mutualFundMapper.toDto(mutualFund);
    }

    /**
     * Delete the mutualFund by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MutualFund : {}", id);
        mutualFundRepository.delete(id);
    }

	public List<MutualFund> getmutualfund(Long uid) {
		return mutualFundRepository.findByUserid(uid);
	}
}
