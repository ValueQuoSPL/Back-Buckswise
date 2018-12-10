package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.AtlernateInvestment;
import com.valuequo.buckswise.service.dto.AtlernateInvestmentDTO;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.http.ResponseEntity;


/**
 * Spring Data JPA repository for the AtlernateInvestment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtlernateInvestmentRepository extends JpaRepository<AtlernateInvestment, Long> {

	List<AtlernateInvestment> findByUserId(Long user_id);

	AtlernateInvestment findById(Long id);

//	ResponseEntity<AtlernateInvestmentDTO> findByUserId(Long user_id);

//	Optional<AtlernateInvestment> findOnebyUserId(Long user_id);

}
