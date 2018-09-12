package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Grossdeduct;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Grossdeduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrossdeductRepository extends JpaRepository<Grossdeduct, Long> {

}
