package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Familyprofile;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.repository.FamilyprofileRepository;
import com.valuequo.buckswise.service.dto.FamilyprofileDTO;
import com.valuequo.buckswise.service.mapper.FamilyprofileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Familyprofile.
 */
@Service
@Transactional
public class FamilyprofileService {

    private final Logger log = LoggerFactory.getLogger(FamilyprofileService.class);

    private final FamilyprofileRepository familyprofileRepository;

    private final FamilyprofileMapper familyprofileMapper;

    public FamilyprofileService(FamilyprofileRepository familyprofileRepository, FamilyprofileMapper familyprofileMapper) {
        this.familyprofileRepository = familyprofileRepository;
        this.familyprofileMapper = familyprofileMapper;
    }

    /**
     * Save a familyprofile.
     *
     * @param familyprofileDTO the entity to save
     * @return the persisted entity
     */
    public FamilyprofileDTO save(FamilyprofileDTO familyprofileDTO) {
        log.debug(" in service Request to save Familyprofile : {}", familyprofileDTO.getUid());
        Familyprofile familyprofile = familyprofileMapper.toEntity(familyprofileDTO);
        System.out.println("family profile: " + familyprofile);
        familyprofile = familyprofileRepository.save(familyprofile);
        return familyprofileMapper.toDto(familyprofile);
    	
    }
    public FamilyprofileDTO update(FamilyprofileDTO familyprofileDTO) {
    	Long uid = familyprofileDTO.getUid();
    	System.out.println("value: " + uid);
    	List<Familyprofile> pf = familyprofileRepository.findByUid(uid);
    	for(Familyprofile familyprofile: pf) {
    		familyprofile.setRelationship(familyprofileDTO.getRelationship());
    		familyprofile.setFirstname(familyprofileDTO.getFirstname());
    		familyprofile.setMiddlename(familyprofileDTO.getMiddlename());
    		familyprofile.setLastname(familyprofileDTO.getLastname());
    		familyprofile.setDateOfBirth(familyprofileDTO.getDateOfBirth());
    		familyprofile.setEarncheck(familyprofileDTO.getEarncheck());
    		familyprofile.setEmail(familyprofileDTO.getEmail());
    		familyprofile.setPhonenumber(familyprofileDTO.getPhonenumber());
    		familyprofileRepository.save(familyprofile);
    	}
    	
    	return null;
    }

    /**
     * Get all the familyprofiles.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FamilyprofileDTO> findAll() {
        log.debug("Request to get all Familyprofiles");
        return familyprofileRepository.findAll().stream()
            .map(familyprofileMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one familyprofile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public FamilyprofileDTO findOne(Long uid) {
        log.debug("Request to get Familyprofile : {}", uid);
        Familyprofile familyprofile = familyprofileRepository.findOne(uid);
        return familyprofileMapper.toDto(familyprofile);
    }

    /**
     * Delete the familyprofile by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Familyprofile : {}", id);
        familyprofileRepository.delete(id);
    }
    public List<Familyprofile> getFamilyProfileById(Long uid)
    {
    	return familyprofileRepository.findByUid(uid);
    }
}
