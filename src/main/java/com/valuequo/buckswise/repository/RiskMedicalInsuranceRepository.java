package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.RiskMedicalInsurance;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RiskMedicalInsurance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskMedicalInsuranceRepository extends JpaRepository<RiskMedicalInsurance, Long> {

	List<RiskMedicalInsurance> findByUserid(Long userid);
	
}
