package com.valuequo.buckswise.repository;

import java.util.List;

import com.valuequo.buckswise.domain.Advisor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Advisor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {

    List<Advisor> findByAid(Long aid);

    // List<Income> findByUserid(Long userid);
}
