package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Gross;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Gross entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrossRepository extends JpaRepository<Gross, Long> {

}
