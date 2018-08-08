package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.GeneralInsurance;

@Repository
public interface GeneralInsuraanceRepository extends JpaRepository<GeneralInsurance, Long>{

	List<GeneralInsurance> findByUserid(int userid);

}
