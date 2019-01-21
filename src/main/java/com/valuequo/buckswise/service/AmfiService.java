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
        List<Amfi> nav = amfiRepository.findAll();
        int size = nav.size();
        int count = 0;
        List<Amfi> anf = new ArrayList<Amfi>();
        for (Amfi result: nav) {
            Amfi amfi = new Amfi();
            amfi.setSchemeName(result.getSchemeName());
            anf.add(amfi);
            for (Amfi var : anf) {
                if((count + 1) % 1000 == 0 || (count + 1) == size) {
                    System.out.println(var.getSchemeName());
                    // amfiRepository.save(amf);
                }
                count++;
            };
            // String str = result.getSchemeName();
           
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