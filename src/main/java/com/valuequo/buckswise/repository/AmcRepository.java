package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Amc;
import com.valuequo.buckswise.domain.Amfi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AmcRepository extends JpaRepository<Amc, String> {
	
	// @Query("select a.amc_name, a.amc_code from Amc a")
	// List<Amc> findAmcode();
}