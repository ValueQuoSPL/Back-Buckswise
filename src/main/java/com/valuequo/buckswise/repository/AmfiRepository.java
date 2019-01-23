package com.valuequo.buckswise.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.valuequo.buckswise.domain.Amfi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AmfiRepository extends JpaRepository<Amfi, Long> {

	@Query("select a from Amfi a where a.amc_code = :name")
	List<Amfi> findByAmc_code(@Param("name") String name );
	
	@Transactional
  	@Modifying
	@Query("UPDATE Amfi a SET a.amc_code = :amc_code WHERE a.SchemeName LIKE CONCAT('%',:amc_code,'%')")
	void update(@Param("amc_code") String amc_code);

}