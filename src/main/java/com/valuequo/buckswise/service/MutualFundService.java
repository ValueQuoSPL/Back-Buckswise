package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Mutualfund.
 */
@Service
@Transactional
public class MutualFundService {

    private final Logger log = LoggerFactory.getLogger(MutualFundService.class);

    private final MutualFundRepository mutualfundRepository;

    private final MutualFundMapper mutualfundMapper;

    public MutualFundService(MutualFundRepository mutualfundRepository, MutualFundMapper mutualfundMapper) {
        this.mutualfundRepository = mutualfundRepository;
        this.mutualfundMapper = mutualfundMapper;
    }

    /**
     * Save a mutualfund.
     *
     * @param mutualfundDTO the entity to save
     * @return the persisted entity
     */
    public MutualFundDTO save(MutualFundDTO mutualfundDTO) {
        log.debug("Request to save Mutualfund : {}", mutualfundDTO);
        MutualFund mutualfund = mutualfundMapper.toEntity(mutualfundDTO);
        mutualfund.setAvailable(mutualfund.getCurrentvalue());
        mutualfund = mutualfundRepository.save(mutualfund);
        return mutualfundMapper.toDto(mutualfund);
    }

    /**
     * Get all the mutualfunds.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<MutualFundDTO> findAll() {
        log.debug("Request to get all Mutualfunds");
        return mutualfundRepository.findAll().stream()
            .map(mutualfundMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one mutualfund by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public List<MutualFund> getUserDetail(Long uid) {
    	return mutualfundRepository.findByUserid(uid);
    }
    @Transactional(readOnly = true)
    public List<MutualFund> getUserDetailById(Long id) {
    	return mutualfundRepository.findByid(id);
    }

    /**
     * Delete the mutualfund by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Mutualfund : {}", id);
        mutualfundRepository.delete(id);
    }

    /**
     * author - Pratik
     * @param id
     * @param avail
     */
	public void updateAvailable(Long id, String avail) {
        MutualFund mf = mutualfundRepository.findById(id);
        mf.setAvailable(avail);
        mutualfundRepository.save(mf);
	}

}
