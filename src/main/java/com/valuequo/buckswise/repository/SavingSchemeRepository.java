package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.SavingScheme;

import afu.org.checkerframework.checker.units.qual.Time;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SavingScheme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SavingSchemeRepository extends JpaRepository<SavingScheme, Long> {
//	SavingScheme findByType(String type);

	List<SavingScheme> findByUid(Long uid);
//	List<SavingScheme> findByUid(Long uid);
	List<SavingScheme> findByid(Long id);
}
