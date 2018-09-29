package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Assetmapping;
import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.domain.GoalSet;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Assetmapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetmappingRepository extends JpaRepository<Assetmapping, Long> {
	List<Assetmapping> findByUid(Long uid);
//	List<Chit> findByUserid(Long userid);

}
