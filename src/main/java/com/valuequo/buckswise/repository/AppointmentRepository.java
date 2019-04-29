package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Appointment;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
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
	
	@Query("select a from Appointment a where a.date >= curdate()")
	List<Appointment> greaterThenToday();
}
