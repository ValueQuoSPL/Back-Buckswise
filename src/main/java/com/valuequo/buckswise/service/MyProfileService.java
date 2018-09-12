package com.valuequo.buckswise.service;
import com.valuequo.buckswise.domain.Familyprofile;
import com.valuequo.buckswise.domain.GoalSet;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.repository.MyProfileRepository;
import com.valuequo.buckswise.service.dto.FamilyprofileDTO;
import com.valuequo.buckswise.service.dto.MyProfileDTO;
import com.valuequo.buckswise.service.mapper.MyProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

	/**
	 * Service Implementation for managing Myprofile.
	 */
	@Service
	@Transactional
	public class MyProfileService {

	    private final Logger log = LoggerFactory.getLogger(MyProfileService.class);

	    private final MyProfileRepository myProfileRepository;

	    private final MyProfileMapper myProfileMapper;

	    public MyProfileService(MyProfileRepository myProfileRepository, MyProfileMapper myProfileMapper) {
	        this.myProfileRepository = myProfileRepository;
	        this.myProfileMapper = myProfileMapper;
	    }
//	    public MyProfile save(MyProfileDTO myProfileDTO) {
//	        log.debug("Request to save Familly : {}", myProfileDTO);
//	        MyProfile myProfile = myProfileMapper.toEntity(myProfileDTO);
//	        myProfile = myProfileRepository.save(myProfile);
//	        return myProfileMapper.toDto(myProfile);
//	    	 log.debug("Request to save myprofile : {}", myProfileDTO);
//			 MyProfile myProfile = new MyProfile();
//			 myProfile.setUid(myProfileDTO.getUid());
//			 myProfile.setFirstName(myProfileDTO.getFirstName());
//			 myProfile.setMiddleName(myProfileDTO.getMiddleName());
//			 myProfile.setLastName(myProfileDTO.getLastName());
//			 myProfile.setDob(myProfileDTO.getDob());
//			 myProfile.setGender(myProfileDTO.getGender());
//			 myProfile.setMaritalStatus(myProfileDTO.getMaritalStatus());
//			 myProfile.setMobileNumber(myProfileDTO.getMobileNumber());
//			 myProfile.setAlternateNumber(myProfileDTO.getAlternateNumber());
//			 myProfile.setOccupation(myProfileDTO.getOccupation());
//			 myProfile.setCompany(myProfileDTO.getCompany());
//			 myProfile.setHowDidYouKnow(myProfile.getHowDidYouKnow());
//			 myProfile.setAddress(myProfileDTO.getAddress());
//			 myProfile.setEmailId(myProfileDTO.getEmailId());
//			 myProfile.setPan(myProfile.getPan());
//			 myProfile.setCountry(myProfileDTO.getCountry());
//			 myProfile.setState(myProfileDTO.getState());
//			 myProfile.setCity(myProfile.getCity());
//			 myProfile.setPin(myProfile.getPin());
//			 System.out.println("the data in myprofile"+myProfile);
//			 myProfileRepository.save(myProfile);
//			 return myProfile;
//	    }
	    public MyProfileDTO save(MyProfileDTO myProfileDTO) {
	        log.debug(" in service Request to save Familyprofile : {}", myProfileDTO.getUid());
	        MyProfile myprofile = myProfileMapper.toEntity(myProfileDTO);
	        System.out.println("family profile: " + myprofile);
	        myprofile = myProfileRepository.save(myprofile);
	        return myProfileMapper.toDto(myprofile);
	    	
	    }

	    /**
	     * Get all the famillies.
	     *
	     * @return the list of entities
	     */
	    @Transactional(readOnly = true)
	    public List<MyProfileDTO> findAll() {
	        log.debug("Request to get all Famillies");
	        return myProfileRepository.findAll().stream()
	            .map(myProfileMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));
	    }

//	   
//	    @Transactional(readOnly = true)
//	    	public MyProfileDTO findOne(Long UID){
//	        log.debug("Request to get Familly : {}", UID);
//	        MyProfile myProfile= myProfileRepository.findOne(UID);
//	        return myProfileMapper.toDto(myProfile);
//	    }
//
	    public List<MyProfile> getMyProfileById(Long uid)
	    {
	    	return myProfileRepository.findByUid(uid);
	    }

//	    /**
//	     * Delete the familly by id.
//	     *
//	     * @param id the id of the entity
//	     */
//	    public void delete(Long id) {
//	        log.debug("Request to delete Familly : {}", id);
//	        famillyRepository.delete(id);
//	    }
	    
	    public MyProfileDTO update(MyProfileDTO myProfileDTO) {
	    	Long uid = myProfileDTO.getUid();
	    	System.out.println("value: " + uid);
	    	List<MyProfile> myprof = myProfileRepository.findByUid(uid);
	    	for(MyProfile myprofile: myprof) {
	    		myprofile.setFirstName(myProfileDTO.getFirstName());
	    		myprofile.setMiddleName(myProfileDTO.getMiddleName());
	    		myprofile.setLastName(myProfileDTO.getLastName());
	    		myprofile.setDob(myProfileDTO.getDob());
	    		myprofile.setGender(myProfileDTO.getGender());
	    		myprofile.setMaritalStatus(myProfileDTO.getMaritalStatus());
	    		myprofile.setMobileNumber(myProfileDTO.getMobileNumber());
	    		myprofile.setAlternateNumber(myProfileDTO.getAlternateNumber());
	    		myprofile.setOccupation(myProfileDTO.getOccupation());
	    		myprofile.setCompany(myProfileDTO.getCompany());
	    		myprofile.setHowDidYouKnow(myProfileDTO.getHowDidYouKnow());
	    		myprofile.setAddress(myProfileDTO.getAddress());
	    		myprofile.setEmailId(myProfileDTO.getEmailId());
	    		myprofile.setPan(myProfileDTO.getPan());
	    		myprofile.setCountry(myProfileDTO.getCountry());
	    		myprofile.setState(myProfileDTO.getState());
	    		myprofile.setCity(myProfileDTO.getCity());
	    		myprofile.setPin(myProfileDTO.getPin());
	    		myProfileRepository.save(myprofile);
	    	}
	    	
	    	return null;
	    }
	}



