package com.valuequo.buckswise.repository;

import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.service.dto.MutualFundDTO;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Mutualfund entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Long> {
	List<MutualFund> findByUserid(Long userid);
	MutualFund findByid(Long id);
	MutualFund findById(Long id);

	@Query("select a.id, a.schemecode, a.unitbalance, a.sipamount from MutualFund a where a.sipday =:date")
	List<Object> findBySipday(@Param("date") String current);
	
	/**
	*Author Sandeep Pote
	*@param Units and Id  
	*/
	@Transactional
  	@Modifying
	@Query("UPDATE MutualFund a SET a.unitbalance =:units where a.id =:id")
	void update(@Param("units") String units, @Param("id") Long id);
}
