package com.valuequo.buckswise.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.valuequo.buckswise.domain.GoalSet;
import com.valuequo.buckswise.domain.Income;
import com.valuequo.buckswise.domain.SavingScheme;
import com.valuequo.buckswise.repository.GoalSetRepository;
import com.valuequo.buckswise.service.mapper.GoalSetMapper;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.valuequo.buckswise.service.dto.GoalSetDTO;
import com.valuequo.buckswise.service.dto.SavingDTO;

@Service
public class GoalSetService {
	private final Logger log = LoggerFactory.getLogger(GoalSetService.class);
	private GoalSetRepository goalSetRepository;

	private GoalSetMapper goalSetMapper;
	
	 public GoalSetService(GoalSetRepository goalSetRepository, GoalSetMapper goalsetMapper) {
	        this.goalSetRepository = goalSetRepository;
	        this.goalSetMapper = goalsetMapper;
	    }
	 public GoalSet save(GoalSetDTO goalsetDTO )  {
		 log.debug("Request to save goal : {}", goalsetDTO);
		 GoalSet goalset = new GoalSet();
		 goalset.setUid(goalsetDTO.getUID());
		 goalset.setGoaltype(goalsetDTO.getGoaltype());
		 goalset.setGoalname(goalsetDTO.getGoalname());
		 goalset.setGoalpriority(goalsetDTO.getPriority());
		 goalset.setYeartogoal(goalsetDTO.getYeartogoal());
		 goalset.setPresentcost(goalsetDTO.getPresentcost());
		 goalset.setFuturecost(goalsetDTO.getFuturecost());
		 goalset.setRequiremonthinvest(goalsetDTO.getRequiremonthinvest());
		 goalset.setFundshortage(goalsetDTO.getFundshortage());
		 goalset.setCrationdate(goalsetDTO.getDateofcreation());
		 goalset.setGoalNotes(goalsetDTO.getNotes());
		 goalset.setLinkassets(goalsetDTO.getLinkassets());
		 goalset.setAssetname(goalsetDTO.getAssetname());
		 goalset.setValue(goalsetDTO.getValue());
		 goalset.setValuetomap(goalsetDTO.getValuetomap());
		 System.out.println("the data in goalset"+goalset);
		 goalSetRepository.save(goalset);
		 return goalset;
	}
	 	public List<GoalSet> updateData(GoalSetDTO goalsetDTO) {
			Long id = goalsetDTO.getId();
			List<GoalSet> update = goalSetRepository.findById(id);
			for(GoalSet goalset: update) {
//				up.setNum(savingDTO.getNum());
//				up.setOrganisation_name(savingDTO.getOrganisation_name());
//				up.setAmount_invested(savingDTO.getAmount_invested());
//				up.setDividend_type(savingDTO.getDividend_type());
//				up.setInvestor_name(savingDTO.getInvestor_name());
//				up.setRate_of_interest(savingDTO.getRate_of_interest());
//				up.setTenure(savingDTO.getTenure());
//				up.setStart_date(savingDTO.getStart_date());
//				up.setEnd_date(savingDTO.getEnd_date());
//				up.setFund_value(savingDTO.getFund_value());
//				up.setAs_of_date(savingDTO.getAs_of_date());
//				up.setNotes(savingDTO.getNotes());
//				up.setType(savingDTO.getType());
//				savingSchemeRepository.save(up);
				 goalset.setUid(goalsetDTO.getUID());
				 goalset.setGoaltype(goalsetDTO.getGoaltype());
				 goalset.setGoalname(goalsetDTO.getGoalname());
				 goalset.setGoalpriority(goalsetDTO.getPriority());
				 goalset.setYeartogoal(goalsetDTO.getYeartogoal());
				 goalset.setPresentcost(goalsetDTO.getPresentcost());
				 goalset.setFuturecost(goalsetDTO.getFuturecost());
				 goalset.setRequiremonthinvest(goalsetDTO.getRequiremonthinvest());
				 goalset.setFundshortage(goalsetDTO.getFundshortage());
				 goalset.setCrationdate(goalsetDTO.getDateofcreation());
				 goalset.setGoalNotes(goalsetDTO.getNotes());
				 goalset.setLinkassets(goalsetDTO.getLinkassets());
				 goalset.setAssetname(goalsetDTO.getAssetname());
				 goalset.setValue(goalsetDTO.getValue());
				 goalset.setValuetomap(goalsetDTO.getValuetomap());
				 System.out.println("the data in goalset"+goalset);
				 goalSetRepository.save(goalset);
			}
			return null;
			 
		}
	 
	 @Transactional(readOnly = true)
	    public List<GoalSetDTO> findAll() {
	        log.debug("Request to get all goal");
	        return goalSetRepository.findAll().stream()
	            .map(goalSetMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	    }
	    public List<GoalSet> getGoalById(Long uid)
	    {
	    	String presentCost;
	    	String yearGoal;
	    	
	    	List<GoalSet> result = goalSetRepository.findByUid(uid);
	    	for(GoalSet res: result) {
	    		presentCost = res.getPresentcost();
	    		yearGoal = res.getYeartogoal();
	    		res.setFuturecost(calculateFutureCost(presentCost, yearGoal));
	    	}
	    	 return result;
	    }
	    public List<GoalSet> getGoalId(Long id)
	    {
	    	return goalSetRepository.findById(id);
	    }
		public void update(int goalId, String check) {
			List<GoalSet> result = goalSetRepository.findById((long) goalId);
			for(GoalSet res: result) {
				res.setCheck(check);
				goalSetRepository.save(res);
			}
		}
		public void updateGoalNotes(int id, String goalnote) {
			List<GoalSet> result = goalSetRepository.findById((long) id);
			for(GoalSet res: result) {
				res.setGoalNotes(goalnote);
				goalSetRepository.save(res);
			}
		}
		
		// calculate the future cost
		public String calculateFutureCost(String presentCost, String yearGoal) {
			double inflation = 0.07;
			double yearToGoal = Double.parseDouble(yearGoal);
			double presentToCost = Double.parseDouble(presentCost);
			double futureCost =  Math.round(presentToCost * Math.pow(1 + inflation, yearToGoal));
			String futureCt = Double.toString(futureCost);
			return futureCt;
		}

}
