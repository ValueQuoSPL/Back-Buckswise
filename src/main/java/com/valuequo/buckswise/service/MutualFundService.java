package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.AmfiRepository;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.valuequo.buckswise.domain.Amfi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transaction;

/**
 * Service Implementation for managing Mutualfund.
 */
@Service
public class MutualFundService {

    private final Logger log = LoggerFactory.getLogger(MutualFundService.class);

    private final MutualFundRepository mutualfundRepository;

    private final MutualFundMapper mutualfundMapper;

    private final AmfiRepository amfiRepository;

    private SessionFactory hibernateFactory;

    Date currentDate = new Date();

    Session session = null;

    org.hibernate.Transaction tx;

    public MutualFundService(MutualFundRepository mutualfundRepository, MutualFundMapper mutualfundMapper, AmfiRepository amfiRepository, EntityManagerFactory factory) {
        this.mutualfundRepository = mutualfundRepository;
        this.mutualfundMapper = mutualfundMapper;
        this.amfiRepository = amfiRepository;
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
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
        for(MutualFund result: mf) {
            String schemeCode = result.getSchemecode();
            String unit = result.getUnitbalance();
            Date p_date = result.getP_date();
            Double day = dateDiff(p_date);
            String amfi =  getNavValue(schemeCode);
            String netCurrent = calCurrentValue(schemeCode, amfi, unit);
            result.setCurrentvalue(netCurrent);
            result.setHoldingdays(day.toString());
        }
        return mf;
    }


 /**
     * Author sandeep
     *
     * @param schemeCode
     * day basis getting nav.
     */
    private String list;
    public String getNavValue(String schemeCode) {
    Date currentDate = new Date();
    int day = currentDate.getDate();
    String dayVal = Integer.toString(day);
    String currentDay = "day" + dayVal;
        try {
            session = this.hibernateFactory.openSession();
            tx = session.beginTransaction();
            String sqlQuery = "Select a." +currentDay+ " from Amfi a where a.SchemeCode =" +schemeCode;
            Query query = session.createQuery(sqlQuery);
            this.list = ((org.hibernate.query.Query) query).uniqueResult().toString();
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return this.list;
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
        int day = current.getDate();
        String days = Integer.toString(day);
        List<Object> mFund = (List<Object>) mutualfundRepository.findBySipday(days);
        Iterator itr = mFund.iterator();
        while(itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
                Long id = (Long) obj[0];
                String schemeCode = String.valueOf(obj[1]);
                String unitbalance = String.valueOf(obj[2]);
                String sipamount = String.valueOf(obj[3]);
                String nav = getNavValue(schemeCode);
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
    	MutualFund mf = mutualfundRepository.findByid(id);
             String schemeCode = mf.getSchemecode();
             String unit = mf.getUnitbalance();
             Date p_date = mf.getP_date();
             Double day = dateDiff(p_date);
             String amfi =  getNavValue(schemeCode);
             String netCurrent = calCurrentValue(schemeCode, amfi, unit);
             mf.setCurrentvalue(netCurrent);
             mf.setHoldingdays(day.toString());
         return mf;
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
