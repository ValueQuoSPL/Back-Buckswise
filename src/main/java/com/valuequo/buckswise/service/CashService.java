package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Cash;
import com.valuequo.buckswise.repository.CashRepository;
import com.valuequo.buckswise.service.dto.CashDTO;
import com.valuequo.buckswise.service.mapper.CashMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Cash.
 */
@Service
@Transactional
public class CashService
{
    private final Logger log = LoggerFactory.getLogger(CashService.class);

    private final CashRepository cashRepository;

    private final CashMapper cashMapper;

    public CashService(CashRepository cashRepository, CashMapper cashMapper)
    {
        this.cashRepository = cashRepository;
        this.cashMapper = cashMapper;
    }

    /**
     * Save a cash.
     *
     * @param cashDTO the entity to save
     * @return the persisted entity
     */
    public CashDTO save(CashDTO cashDTO)
    {
        log.debug("Request to save Cash : {}", cashDTO);
        Cash cash = cashMapper.toEntity(cashDTO);
        cash.setAvailable(cash.getAmount());
        cash = cashRepository.save(cash);
        return cashMapper.toDto(cash);
    }

    /**
     * Get all the cash.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<CashDTO> findAll(Pageable pageable)
    {
        log.debug("Request to get all Cash");
        return cashRepository.findAll(pageable)
            .map(cashMapper::toDto);
    }

    /**
     * Get one cash by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public CashDTO findOne(Long id) {
        log.debug("Request to get Cash : {}", id);
        Cash cash = cashRepository.findOne(id);
        return cashMapper.toDto(cash);
    }

    /**
     * Delete the cash by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Cash : {}", id);
        cashRepository.delete(id);
    }

	public List<Cash> getCash(Long userid) {
		return cashRepository.findByUserid(userid);
	}

	public void updateAvailable(Long id, String avail) {
        Cash cash = cashRepository.findById(id);
        cash.setAvailable(avail);
        cashRepository.save(cash);
	}
}
