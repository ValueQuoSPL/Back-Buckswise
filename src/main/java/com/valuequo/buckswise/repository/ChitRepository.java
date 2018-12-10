package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Chit;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Chit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChitRepository extends JpaRepository<Chit, Long> {

	List<Chit> findByUserid(Long userid);

	Chit findById(Long id);

}
