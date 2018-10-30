package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Appointment;
import com.valuequo.buckswise.domain.Chit;
import com.valuequo.buckswise.service.dto.AppointmentDTO;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Appointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	@Query("select a from Appointment a where a.uid = :uid AND a.status= 'confirm'")
	List<Appointment> findByUid(@Param("uid") Long uid);
	
	List<Appointment> findById(Long id);
}

