package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Others;
import com.valuequo.buckswise.repository.OthersRepository;
import com.valuequo.buckswise.service.dto.OthersDTO;
import com.valuequo.buckswise.service.mapper.OthersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Others.
 */
@Service
@Transactional
public class OthersService {

    private final Logger log = LoggerFactory.getLogger(OthersService.class);

    private final OthersRepository othersRepository;

    private final OthersMapper othersMapper;

    public OthersService(OthersRepository othersRepository, OthersMapper othersMapper) {
        this.othersRepository = othersRepository;
        this.othersMapper = othersMapper;
    }

    /**
     * Save a others.
     *
     * @param othersDTO the entity to save
     * @return the persisted entity
     */
    public OthersDTO save(OthersDTO othersDTO) {
        log.debug("Request to save Others : {}", othersDTO);
        Others others = othersMapper.toEntity(othersDTO);
        others = othersRepository.save(others);
        return othersMapper.toDto(others);
    }

    /**
     * Get all the others.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<OthersDTO> findAll() {
        log.debug("Request to get all Others");
        return othersRepository.findAll().stream()
            .map(othersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one others by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public OthersDTO findOne(Long uid) {
        log.debug("Request to get Others : {}", uid);
        Others others = othersRepository.findOne(uid);
        return othersMapper.toDto(others);
    }

    /**
     * Delete the others by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Others : {}", id);
        othersRepository.delete(id);
    }
}
