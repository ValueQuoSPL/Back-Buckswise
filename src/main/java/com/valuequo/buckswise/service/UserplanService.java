package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Userplan;
import com.valuequo.buckswise.repository.UserplanRepository;
import com.valuequo.buckswise.service.dto.UserplanDTO;
import com.valuequo.buckswise.service.mapper.UserplanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Userplan.
 */
@Service
@Transactional
public class UserplanService {

    private final Logger log = LoggerFactory.getLogger(UserplanService.class);

    private final UserplanRepository userplanRepository;

    private final UserplanMapper userplanMapper;

    public UserplanService(UserplanRepository userplanRepository, UserplanMapper userplanMapper) {
        this.userplanRepository = userplanRepository;
        this.userplanMapper = userplanMapper;
    }

    /**
     * Save a userplan.
     *
     * @param userplanDTO the entity to save
     * @return the persisted entity
     */
    public UserplanDTO save(UserplanDTO userplanDTO) {
        log.debug("Request to save Userplan : {}", userplanDTO);
        Userplan userplan = userplanMapper.toEntity(userplanDTO);
        userplan = userplanRepository.save(userplan);
        return userplanMapper.toDto(userplan);
    }

    /**
     * Get all the userplans.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserplanDTO> findAll() {
        log.debug("Request to get all Userplans");
        return userplanRepository.findAll().stream()
            .map(userplanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

	    public List<Userplan> getUser(Long uid)
	    {
	    	return userplanRepository.findByUid(uid);
	    }
    /**
     * Get one userplan by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    // @Transactional(readOnly = true)
    // public Optional<UserplanDTO> findOne(Long id) {
    //     log.debug("Request to get Userplan : {}", id);
    //     return userplanRepository.findById(id)
    //         .map(userplanMapper::toDto);
    // }

    /**
     * Delete the userplan by id.
     *
     * @param id the id of the entity
     */
    // public void delete(Long id) {
    //     log.debug("Request to delete Userplan : {}", id);
    //     userplanRepository.deleteById(id);
    // }
}
