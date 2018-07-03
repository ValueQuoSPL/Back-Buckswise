package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Homededuction;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Homededuction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HomedeductionRepository extends JpaRepository<Homededuction, Long> {

}
