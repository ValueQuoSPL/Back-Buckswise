package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Property entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

	List<Property> findByUserid(Long userid);

	Property findById(Long id);

}
