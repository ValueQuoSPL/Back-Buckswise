package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.FutureOption;
import com.valuequo.buckswise.repository.FutureOptionRepository;
import com.valuequo.buckswise.service.dto.FutureOptionDTO;
import com.valuequo.buckswise.service.mapper.FutureOptionMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing FutureOption.
 */
@Service
@Transactional
public class FutureOptionService {

    private final Logger log = LoggerFactory.getLogger(FutureOptionService.class);

    private final FutureOptionRepository futureOptionRepository;

    private final FutureOptionMapper futureOptionMapper;

    public FutureOptionService(FutureOptionRepository futureOptionRepository, FutureOptionMapper futureOptionMapper) {
        this.futureOptionRepository = futureOptionRepository;
        this.futureOptionMapper = futureOptionMapper;
    }

    /**
     * Save a futureOption.
     *
     * @param futureOptionDTO the entity to save
     * @return the persisted entity
     */
    public FutureOptionDTO save(FutureOptionDTO futureOptionDTO) {
        log.debug("Request to save FutureOption : {}", futureOptionDTO);
        FutureOption futureOption = futureOptionMapper.toEntity(futureOptionDTO);
        futureOption = futureOptionRepository.save(futureOption);
        return futureOptionMapper.toDto(futureOption);
    }

    /**
     * Get all the futureOptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<FutureOptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FutureOptions");
        return futureOptionRepository.findAll(pageable)
            .map(futureOptionMapper::toDto);
    }

    /**
     * Get one futureOption by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FutureOptionDTO findOne(Long id) {
        log.debug("Request to get FutureOption : {}", id);
        FutureOption futureOption = futureOptionRepository.findOne(id);
        return futureOptionMapper.toDto(futureOption);
    }

    /**
     * Delete the futureOption by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FutureOption : {}", id);
        futureOptionRepository.delete(id);
    }

	public List<FutureOption> getfutureOption(Long userid) {
		return futureOptionRepository.findByUserid(userid);
	}
}
