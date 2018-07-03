package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Other;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Other entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OtherRepository extends JpaRepository<Other, Long> {

}
