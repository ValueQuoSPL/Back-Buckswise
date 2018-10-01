package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Eightycdeduct;
import com.valuequo.buckswise.domain.Otherdeduction;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Otherdeduction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OtherdeductionRepository extends JpaRepository<Otherdeduction, Long> {

	List<Otherdeduction> findByUid(int uid);
}
