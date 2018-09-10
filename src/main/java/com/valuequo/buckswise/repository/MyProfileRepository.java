package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.valuequo.buckswise.domain.MyProfile;
import com.valuequo.buckswise.service.dto.MyProfileDTO;
	
@SuppressWarnings("unused")
@Repository
public interface MyProfileRepository extends JpaRepository<MyProfile, Long> {

	List<MyProfile> findByUid(Long uid);

}


