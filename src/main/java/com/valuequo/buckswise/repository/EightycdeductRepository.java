package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Eightycdeduct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Eightycdeduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EightycdeductRepository extends JpaRepository<Eightycdeduct, Long> {

}
