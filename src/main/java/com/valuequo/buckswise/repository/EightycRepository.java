package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Eightyc;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Eightyc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EightycRepository extends JpaRepository<Eightyc, Long> {

}
