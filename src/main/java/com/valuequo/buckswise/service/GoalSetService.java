package com.valuequo.buckswise.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.valuequo.buckswise.domain.GoalSet;
import com.valuequo.buckswise.repository.GoalSetRepository;
import com.valuequo.buckswise.service.mapper.GoalSetMapper;
import com.valuequo.buckswise.service.dto.GoalSetDTO;

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
		 System.out.println("the data in goalset"+goalset);
		 goalSetRepository.save(goalset);
		 return goalset;
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
	    	return goalSetRepository.findByUid(uid);
	    }

}
