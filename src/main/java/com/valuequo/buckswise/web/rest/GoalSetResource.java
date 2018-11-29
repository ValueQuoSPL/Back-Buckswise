package com.valuequo.buckswise.web.rest;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.GoalSet;
import com.valuequo.buckswise.domain.SavingScheme;
import com.valuequo.buckswise.service.GoalSetService;
import com.valuequo.buckswise.service.dto.GoalSetDTO;
import com.valuequo.buckswise.service.dto.SavingDTO;

import afu.org.checkerframework.checker.units.qual.Time;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;

@RestController
@RequestMapping("/api")
public class GoalSetResource {
	private final Logger log = LoggerFactory.getLogger(GoalSetResource.class);
//	private static final String ENTITY_NAME = "GoalSet";
	private final GoalSetService goalsetservice;
	private static final String ENTITY_NAME = "goalset";

	 public GoalSetResource(GoalSetService goalsetservice) {
	     this.goalsetservice = goalsetservice;
		}
	 
	 @PostMapping("/goalset")
	    @Timed
	    public GoalSet createGoal(@RequestBody GoalSetDTO goalsetDTO) {
	        log.debug("under goal");
	    	log.debug("REST request to save goal : {}", goalsetDTO);
	        GoalSet goalset =  goalsetservice.save(goalsetDTO);
			return goalset;
	    }
	 
	 @GetMapping("/goalset/{uid}")
	    @Timed
	    public List<GoalSet> getGoalById(@PathVariable Long uid)
	    {
			return goalsetservice.getGoalById(uid);
	    	
	    }
	 @GetMapping("/goalsetbyid/{id}")
	    @Timed
	    public List<GoalSet> getGoalId(@PathVariable Long id)
	    {
			return goalsetservice.getGoalId(id);
	    	
		}

		private int id;
		private String goalnote;
	    @PutMapping("/putgoal")
	    @Time
	    public String updateGoal(@Valid @RequestBody Map<String, Object>[] data) {
			System.out.println("detailnote" + data);
			for(Map<String, Object> entry: data) {
				this.id = (int) entry.get("id");
				this.goalnote = entry.get("notes").toString();
				goalsetservice.updateGoalNotes(this.id, this.goalnote);
			}
	    	//  log.debug("REST request to update goal: {}", goalsetDTO.getId());
	    	 log.debug("radarada");
	    	//  goalsetservice.updateData(goalsetDTO);
	         return null;

	     }
	  
	  private int goalId;
	  private String check;
	  
	 @PutMapping("/putCheck")
	 public String update( @RequestBody Map<String, Object>[] stuff) {
		 for(Map<String, Object> entry: stuff) {
			 this.goalId = (int) entry.get("id");
			 this.check = entry.get("check").toString();
			 System.out.println(this.goalId);
			 System.out.println(this.check);
			 goalsetservice.update(this.goalId, this.check);
		 }
		 return null;
	 }

	 @DeleteMapping("/goaldelete/{id}")
	 @Timed
	 public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
		 log.debug("REST request to delete goal : {}", id);
		 goalsetservice.delete(id);
		 return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	 }
}
