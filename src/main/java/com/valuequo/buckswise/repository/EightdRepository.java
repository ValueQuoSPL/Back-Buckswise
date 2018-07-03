package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Eightd;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Eightd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EightdRepository extends JpaRepository<Eightd, Long> {

}
