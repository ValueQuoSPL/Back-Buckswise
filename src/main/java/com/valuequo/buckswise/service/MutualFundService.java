package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.repository.AmfiRepository;
import com.valuequo.buckswise.repository.MutualFundRepository;
import com.valuequo.buckswise.service.dto.AmfiDTO;
import com.valuequo.buckswise.service.dto.MutualFundDTO;
import com.valuequo.buckswise.service.mapper.MutualFundMapper;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.id.IntegralDataTypeHolder;
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
import java.util.Collection;
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

    int x;

    String[] days = new String[] { "day1", "day2", "day3", "day4", "day5", "day6", "day7", "day8", "day9", "day10",
            "day11", "day12", "day13", "day14", "day15", "day16", "day17", "day18", "day19", "day20", "day21", "day21",
            "day22", "day23", "day24", "day25", "day26", "day27", "day28", "day29", "day30", "day31", };

    public MutualFundService(MutualFundRepository mutualfundRepository, MutualFundMapper mutualfundMapper,
            AmfiRepository amfiRepository, EntityManagerFactory factory) {
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
        return mutualfundRepository.findAll().stream().map(mutualfundMapper::toDto)
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
        List<MutualFund> mf = mutualfundRepository.findByUserid(uid);
        for (MutualFund result : mf) {
            String schemeCode = result.getSchemecode();
            String unit = result.getUnitbalance();
            Date p_date = result.getP_date();
            Double day1 = dateDiff(p_date);
            String amfi = getNavValue(schemeCode);
            String netCurrent = calCurrentValue(schemeCode, amfi, unit);
            result.setCurrentvalue(netCurrent);
            result.setHoldingdays(day1.toString());
        }
        return mf;
    }

    /**
     * Author sandeep
     *
     * @param schemeCode day basis getting nav.
     */
    private String list;

    public String getNavValue(String schemeCode) {

        Date currentDate = new Date();
        int day = currentDate.getDate();
        String dayVal = Integer.toString(day);
        String currentDay = "day" + dayVal;
        System.out.println(currentDay);
        List<String> x = new ArrayList<String>();
        List<Amfi> nav = amfiRepository.getNav(schemeCode);
        for (Amfi var : nav) {
            x.add(var.getDay1());
            x.add(var.getDay2());
            x.add(var.getDay3());
            x.add(var.getDay4());
            x.add(var.getDay5());
            x.add(var.getDay6());
            x.add(var.getDay7());
            x.add(var.getDay8());
            x.add(var.getDay9());
            x.add(var.getDay10());
            x.add(var.getDay11());
            x.add(var.getDay12());
            x.add(var.getDay13());
            x.add(var.getDay14());
            x.add(var.getDay15());
            x.add(var.getDay16());
            x.add(var.getDay17());
            x.add(var.getDay18());
            x.add(var.getDay19());
            x.add(var.getDay20());
            x.add(var.getDay21());
            x.add(var.getDay22());
            x.add(var.getDay23());
            x.add(var.getDay24());
            x.add(var.getDay25());
            x.add(var.getDay26());
            x.add(var.getDay27());
            x.add(var.getDay28());
            x.add(var.getDay29());
            x.add(var.getDay30());
            x.add(var.getDay31());
        }

        for (int i = 0; i < days.length; i++) {
            String monthOfDay = days[i];
            String[] arrOfStr = monthOfDay.split("y", 2);

            for (int j = 2; j <= arrOfStr.length; j++) {
                this.x = Integer.parseInt(arrOfStr[1]);
            }

            if(currentDay.equals(monthOfDay)) {
                for (int z = this.x; z >=1 ; z--) {
                    if(x.get(z) == null) {
                        
                    } else {
                        this.list = x.get(z);
                    }
                }
            }

        }

        return this.list;
    }

    /**
     * author - Sandeep Pote
     * 
     * @param p_date
     */
    private Double dateDiff(Date p_date) {
        Long purchase = p_date.getTime();
        Long currentDate = this.currentDate.getTime();
        Long result = Math.abs(purchase - currentDate);
        Double days = Math.ceil(result / (1000 * 3600 * 24));
        return days;
    }

    /**
     * author - Sandeep Pote
     * 
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
     * author - Sandeep Pote Fire Trigger Every Day at 1:00 AM
     */
    @Scheduled(cron = "0 0/10 * ? * Mon-Fri")
    void unitBalance() throws Exception {
        Date current = new Date();
        int day = current.getDate();
        String days = Integer.toString(day);
        List<Object> mFund = (List<Object>) mutualfundRepository.findBySipday(days);
        Iterator itr = mFund.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Long id = (Long) obj[0];
            String schemeCode = String.valueOf(obj[1]);
            String unitbalance = String.valueOf(obj[2]);
            String sipamount = String.valueOf(obj[3]);
            String nav = getNavValue(schemeCode);
            if (nav.length() > 0) {
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
        String amfi = getNavValue(schemeCode);
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
     * 
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
