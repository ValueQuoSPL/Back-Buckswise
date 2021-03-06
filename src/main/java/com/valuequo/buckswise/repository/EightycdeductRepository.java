package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Eightycdeduct;
import com.valuequo.buckswise.domain.Grossdeduct;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Eightycdeduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EightycdeductRepository extends JpaRepository<Eightycdeduct, Long> {
	
	List<Eightycdeduct> findByUid(int uid);

}
