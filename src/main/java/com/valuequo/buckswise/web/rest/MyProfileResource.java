package com.valuequo.buckswise.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.valuequo.buckswise.domain.GoalSet;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.service.MyProfileService;
import com.valuequo.buckswise.service.dto.MyProfileDTO;
import com.valuequo.buckswise.web.rest.errors.BadRequestAlertException;
import com.valuequo.buckswise.web.rest.util.HeaderUtil;

	@RestController
	@RequestMapping("/api")
	public class MyProfileResource {

		private final Logger log = LoggerFactory.getLogger(MyProfileResource.class);

	    private static final String ENTITY_NAME = "myprofilee";

	    private final MyProfileService myprofileService;
	    
	    public MyProfileResource(MyProfileService myprofileService) {
			this.myprofileService = myprofileService;
		}
//	    @PostMapping("/myprofile")
//	    @Timed
//	    public MyProfile createMyProfile(@RequestBody MyProfileDTO myProfileDTO) throws URISyntaxException {
//	        log.debug("under families");
//	    	log.debug("REST request to save Myprofile : {}", myProfileDTO);
//	    	System.out.println(myProfileDTO.getId());
//	        if (myProfileDTO.getId() != null) {
//	            throw new BadRequestAlertException("A new familly cannot already have an ID", ENTITY_NAME, "idexists");
//	        }
//	        System.out.println("the dto is"+myProfileDTO);
//	        MyProfileDTO result = myprofileService.save(myProfileDTO);
//	        System.out.println("the result is"+result);
//	        return ResponseEntity.created(new URI("/api/myprofile/" + result.getId()))
//	            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//	            .body(result);
//	    	log.debug("under myprofile");
//	    	log.debug("REST request to save myprofile : {}", myProfileDTO);
//	        MyProfile myProfile =  myprofileService.save(myProfileDTO);
//			return myProfile;
//	    }
	    @PostMapping("/myprofile")
	    @Timed
	    public ResponseEntity<MyProfileDTO> createMyprofile(@RequestBody MyProfileDTO myProfileDTO) throws URISyntaxException {
	        log.debug("REST request to save Familyprofile : {}", myProfileDTO.getUid());
	        if (myProfileDTO.getId() != null) {
	            throw new BadRequestAlertException("A new familyprofile cannot already have an ID", ENTITY_NAME, "idexists");
	        }
	        MyProfileDTO result = myprofileService.save(myProfileDTO);
	        return ResponseEntity.created(new URI("/api/familyprofiles/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
	    }
	    
//	    @GetMapping("/myprofile/{uid}")
//	    @Timed
//	    public List<MyProfile> getGoalById(@PathVariable Long uid)
//	    {
//			return myprofileService.findOne(uid);
//	    }
	    @GetMapping("/myprofile/{uid}")
	    @Timed
	    public List<MyProfileDTO> getMyProfileById(@PathVariable Long uid)
	    {
			return myprofileService.getMyProfileById(uid);
	    }
	    @PutMapping("/myprofile")
	    @Timed
	    public ResponseEntity<MyProfileDTO> updateMyprofile(@RequestBody MyProfileDTO myProfileDTO) throws URISyntaxException {
	        log.debug("REST request to update Familyprofile : {}", myProfileDTO);
	        if(myProfileDTO.getUid() == null) {
	            return createMyprofile(myProfileDTO);
	        }
	        MyProfileDTO result = myprofileService.update(myProfileDTO);
	        return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, myProfileDTO.getUid().toString()))
	            .body(result);
	    }
}
