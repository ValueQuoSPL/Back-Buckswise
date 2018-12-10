package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Assetmapping;
import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.domain.GoalSet;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Assetmapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetmappingRepository extends JpaRepository<Assetmapping, Long> {

	@Query("select a from Assetmapping a where a.uid = :uid AND a.goalid = :id")
	List<Assetmapping> findByUid(@Param("uid") Long uid, @Param("id") int goalid);
//	List<Chit> findByUserid(Long userid);

}
