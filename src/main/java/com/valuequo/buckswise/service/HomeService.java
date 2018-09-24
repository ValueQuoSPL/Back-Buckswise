package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Home;
import com.valuequo.buckswise.repository.HomeRepository;
import com.valuequo.buckswise.service.dto.HomeDTO;
import com.valuequo.buckswise.service.mapper.HomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Home.
 */
@Service
@Transactional
public class HomeService {

    private final Logger log = LoggerFactory.getLogger(HomeService.class);

    private final HomeRepository homeRepository;

    private final HomeMapper homeMapper;

    public HomeService(HomeRepository homeRepository, HomeMapper homeMapper) {
        this.homeRepository = homeRepository;
        this.homeMapper = homeMapper;
    }

    /**
     * Save a home.
     *
     * @param homeDTO the entity to save
     * @return the persisted entity
     */
    public HomeDTO save(HomeDTO homeDTO) {
        log.debug("Request to save Home : {}", homeDTO);
        Home home = homeMapper.toEntity(homeDTO);
        home = homeRepository.save(home);
        return homeMapper.toDto(home);
    }

    /**
     * Get all the homes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<HomeDTO> findAll() {
        log.debug("Request to get all Homes");
        return homeRepository.findAll().stream()
            .map(homeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one home by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public HomeDTO findOne(Long uid) {
        log.debug("Request to get Home : {}", uid);
        Home home = homeRepository.findOne(uid);
        return homeMapper.toDto(home);
    }

    /**
     * Delete the home by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Home : {}", id);
        homeRepository.delete(id);
    }
}
