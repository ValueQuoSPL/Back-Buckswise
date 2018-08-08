package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.MutualFund;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MutualFund entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Long> {

	List<MutualFund> findByUserid(Long userid);

}
