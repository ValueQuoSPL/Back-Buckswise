package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.AmfiRepository;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

    private final AmfiRepository amfiRepository;

    Date currentDate = new Date();

    public MutualFundService(MutualFundRepository mutualfundRepository, MutualFundMapper mutualfundMapper, AmfiRepository amfiRepository) {
        this.mutualfundRepository = mutualfundRepository;
        this.mutualfundMapper = mutualfundMapper;
        this.amfiRepository = amfiRepository;
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
        List<MutualFund> mf =  mutualfundRepository.findByUserid(uid);
        System.out.println("mfund" + mf);
        for(MutualFund result: mf) {
            String schemeCode = result.getMfscheme();
            String unit = result.getUnitbalance();
            Date p_date = result.getP_date();
            Double day = dateDiff(p_date);
            String amfi = amfiRepository.findBySchemecode(schemeCode);
            String netCurrent = calCurrentValue(schemeCode, amfi, unit);
            result.setCurrentvalue(netCurrent);
            result.setHoldingdays(day.toString());
        }
        return mf;
    }

    /**
     * author - Sandeep Pote
     * @param p_date
     */
    private Double dateDiff(Date p_date) {
        Long purchase = p_date.getTime();
        Long currentDate = this.currentDate.getTime();
        Long result = Math.abs(purchase - currentDate);
        Double days = Math.ceil( result / (1000 * 3600 * 24));
        return days;
    }
 
    /**
     * author - Sandeep Pote
     * @param schemeCode
     * @param amfi
     * @param unit
     */
    private String calCurrentValue(String schemeCode, String amfi, String unit) {
        Double nav = Double.valueOf(amfi);
        Double currentValue = Double.valueOf(unit);
        String netCurrent = Double.toString(Math.round(nav * currentValue));
        return netCurrent;
    }

    /**
     * author - Sandeep Pote
     * Fire Trigger Every Day at 1:00 AM
     */
    @Scheduled(cron = "0 0/10 * ? * Mon-Fri")
    void unitBalance() throws Exception{
        Date current = new Date();
        List<Object> mFund = (List<Object>) mutualfundRepository.findBySipdate(current);
        Iterator itr = mFund.iterator();
        while(itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
                Long id = (Long) obj[0];
                String schemeCode = String.valueOf(obj[1]);
                String unitbalance = String.valueOf(obj[2]);
                String sipamount = String.valueOf(obj[3]);
                String nav = amfiRepository.findBySchemecode(schemeCode);
                if(nav.length() > 0) {
                    Double netValue = Double.valueOf(nav);
                    Double newBalance = (Double.parseDouble(sipamount) / netValue);
                    String finalValue = new DecimalFormat("##.##").format(newBalance);
                    double unit = (Double.parseDouble(unitbalance) + Double.parseDouble(finalValue));
                    String units = Double.toString(unit);
                    mutualfundRepository.update(units, id);                
                }
            }
    }

    @Transactional(readOnly = true)
    public MutualFund getUserDetailById(Long id) {
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

	public List getNAVdata() {
        List obj = new ArrayList();
        String text = "nav data is working";
        obj.add(text);
		return obj;
	}


}
