package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Advisor;
import com.valuequo.buckswise.repository.AdvisorRepository;
import com.valuequo.buckswise.service.dto.AdvisorDTO;
import com.valuequo.buckswise.service.mapper.AdvisorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Advisor.
 */
@Service
@Transactional
public class AdvisorService {

    private final Logger log = LoggerFactory.getLogger(AdvisorService.class);

    private final AdvisorRepository advisorRepository;

    private final AdvisorMapper advisorMapper;

    public AdvisorService(AdvisorRepository advisorRepository, AdvisorMapper advisorMapper) {
        this.advisorRepository = advisorRepository;
        this.advisorMapper = advisorMapper;
    }

    /**
     * Save a advisor.
     *
     * @param advisorDTO the entity to save
     * @return the persisted entity
     */
    public AdvisorDTO save(AdvisorDTO advisorDTO) {
        log.debug("Request to save Advisor : {}", advisorDTO);
        Advisor advisor = advisorMapper.toEntity(advisorDTO);
        advisor = advisorRepository.save(advisor);
        return advisorMapper.toDto(advisor);
    }

    public Advisor saveRecommendation(Long uid, Long aid,String recotype, String recoby, String recodate, String reco) {
        Advisor ad = new Advisor();
        ad.setUid(uid);
		ad.setAid(aid);
        ad.setRecotype(recotype);
        ad.setReco(reco);
        ad.setRecoby(recoby);
        ad.setRecodate(recodate);
        advisorRepository.save(ad);
        return null;
    }

    /**
     * Get all the advisors.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AdvisorDTO> findAll() {
        log.debug("Request to get all Advisors");
        return advisorRepository.findAll().stream()
            .map(advisorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one advisor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AdvisorDTO findOne(Long id) {
        log.debug("Request to get Advisor : {}", id);
        Advisor advisor = advisorRepository.findOne(id);
        return advisorMapper.toDto(advisor);
    }

    public List<Advisor> findByAid(Long aid, Long uid, String type) {
        return advisorRepository.findByAid(aid, uid, type);
    }

    /**
     * Delete the advisor by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Advisor : {}", id);
        advisorRepository.delete(id);
    }
}
