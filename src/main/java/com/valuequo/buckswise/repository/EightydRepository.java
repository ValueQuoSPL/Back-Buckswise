package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Eightyd;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Eightyd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EightydRepository extends JpaRepository<Eightyd, Long> {

}
