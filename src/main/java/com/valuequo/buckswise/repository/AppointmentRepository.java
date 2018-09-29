package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Appointment;
import com.valuequo.buckswise.service.dto.AppointmentDTO;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Appointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByUid(Long uid);


}
