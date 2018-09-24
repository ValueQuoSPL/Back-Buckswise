package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.RiskLifeInsurance;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RiskLifeInsurance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskLifeInsuranceRepository extends JpaRepository<RiskLifeInsurance, Long> {

	List<RiskLifeInsurance> findByUserid(Long userid);

}
