package com.valuequo.buckswise.repository;

import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.MutualFund;
import com.valuequo.buckswise.service.dto.MutualFundDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Mutualfund entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Long> {
	List<MutualFund> findByUserid(Long userid);
	List<MutualFund> findByid(Long id);
	MutualFund findById(Long id);
}
