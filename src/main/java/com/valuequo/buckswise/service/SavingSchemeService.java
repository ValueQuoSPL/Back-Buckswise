package com.valuequo.buckswise.service;
import com.valuequo.buckswise.domain.SavingScheme;
import com.valuequo.buckswise.repository.SavingSchemeRepository;
import com.valuequo.buckswise.service.dto.SavingSchemeDTO;
import com.valuequo.buckswise.service.mapper.SavingMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing SavingScheme.
 */
@Service
@Transactional
public class SavingSchemeService {

    private final Logger log = LoggerFactory.getLogger(SavingSchemeService.class);

    private final SavingSchemeRepository savingSchemeRepository;

    private final SavingMapper savingMapper;  //mapper class

    public SavingSchemeService(SavingSchemeRepository savingSchemeRepository ,SavingMapper savingMapper) {
        this.savingSchemeRepository = savingSchemeRepository;
       this.savingMapper = savingMapper;
    }

    /**
     * Save a savingScheme.
     *
     * @param savingSchemeDTO the entity to save
     * @return the persisted entity
     */
//    public SavingScheme create(SavingSchemeDTO savingSchemeDTO) {
////        log.debug("Request to save SavingScheme : {}", savingSchemeDTO);
////        SavingScheme savingScheme = savingSchemeMapper.toEntity(savingSchemeDTO);
//    	System.out.println(" under create method");
//    	SavingScheme save = new SavingScheme();
//    	save.setNum(savingSchemeDTO.getNum());
//    	save.setOrganisation_name(savingSchemeDTO.getOrganisation_name());
//    	save.setAmount_invested(savingSchemeDTO.getAmount_invested());
//    	save.setDividend_type(savingSchemeDTO.getDivident_type());
//    	save.setInvestor_name(savingSchemeDTO.getInvestor_name());
//    	save.setRate_of_interest(savingSchemeDTO.getRate_of_interest());
//    	save.setTenure(savingSchemeDTO.getTenure());
//    	save.setStart_date(savingSchemeDTO.getStart_date());
//    	save.setEnd_date(savingSchemeDTO.getEnd_date());
//    	save.setFund_value(savingSchemeDTO.getFund_value());
//    	save.setNotes(savingSchemeDTO.getNotes());
//    	save.setType(savingSchemeDTO.getType());
//    	System.out.println( "under service" + save);
//        savingSchemeRepository.save(save);
//        return save;
//    }
    public SavingSchemeDTO save(SavingSchemeDTO savingSchemeDTO) {
        log.debug("Request to save Mutualfund : {}", savingSchemeDTO);
        SavingScheme savingScheme = savingMapper.toEntity(savingSchemeDTO);
        savingScheme = savingSchemeRepository.save(savingScheme);
        return savingMapper.toDto(savingScheme);
    }
    

    /**
     * Get all the savingSchemes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<SavingSchemeDTO> findAll() {
        log.debug("Request to get all Mutualfunds");
        return savingSchemeRepository.findAll().stream()
            .map(savingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
    /**
     * Get one savingScheme by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
//    @Transactional(readOnly = true)
//    public SavingSchemeDTO findOne(Long id) {
//        log.debug("Request to get SavingScheme : {}", id);
//        SavingScheme savingScheme = savingSchemeRepository.findOne(id);
//        return savingSchemeMapper.toDto(savingScheme);
//    }

    /**
     * Delete the savingScheme by id.
     *
     * @param id the id of the entity
     */
//    public void delete(Long id) {
//        log.debug("Request to delete SavingScheme : {}", id);
//        savingSchemeRepository.delete(id);
//    }
    @Transactional(readOnly = true)
    public List<SavingScheme> getUserDetail(Long uid) {
    	return savingSchemeRepository.findByUid(uid);
    }
    @Transactional(readOnly = true)
    public List<SavingScheme> getUserDetailById(Long id) {
    	return savingSchemeRepository.findByid(id);
    }
    public void delete(Long id) {
        log.debug("Request to delete Mutualfund : {}", id);
        savingSchemeRepository.delete(id);
    }
}
