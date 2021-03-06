package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Home;
import com.valuequo.buckswise.domain.Otherdeduction;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Home entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
	
	List<Home> findByUid(int uid);

}
