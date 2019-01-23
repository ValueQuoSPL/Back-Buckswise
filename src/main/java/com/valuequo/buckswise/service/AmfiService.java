package com.valuequo.buckswise.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.valuequo.buckswise.domain.Amc;
import com.valuequo.buckswise.domain.Amfi;
import com.valuequo.buckswise.repository.AmcRepository;
import com.valuequo.buckswise.repository.AmfiRepository;
import com.valuequo.buckswise.service.dto.AmfiDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmfiService {

    @Autowired
    private AmfiRepository amfiRepository;
    
    @Autowired
    private AmcRepository amcRepository;

    @Transactional
	public void save(ArrayList<AmfiDTO> al) {
        int size = al.size();
        int count = 0;
        ArrayList<Amfi> amf = new ArrayList<Amfi>();
        for(AmfiDTO data : al) {
            Amfi amfi = new Amfi();
            amfi.setDate(data.getDate());
            amfi.setSchemeCode(data.getSchemeCode());
            amfi.setISINDivPayoutISINGrowth(data.getISINDivPayoutISINGrowth());
            amfi.setISINDivReinvestment(data.getISINDivReinvestment());
            amfi.setSchemeName(data.getSchemeName());
            amfi.setNetAssetValue(data.getNetAssetValue());
            amfi.setDate(data.getDate());
            amf.add(amfi);

            if((count + 1) % 500 == 0 || (count + 1) == size) {
                amfiRepository.save(amf);
            }
            count++;
        }
    }
    
    @Transactional
    public void getAmfiCode() {
        List<Amc> amc = amcRepository.findAll();
        for(Amc result : amc) {
            String amc_code = result.getAmc_code();
            amfiRepository.update(amc_code);
        }
    }
    
    public List<Amfi> getAmcName(String name) {
    	List <Amfi> nav = amfiRepository.findByAmc_code(name);
    	return nav;
		
    }
    public List<Amc> getAllAmc() {
    	List <Amc> nav = amcRepository.findAll();
    	return nav;
		
    }
}