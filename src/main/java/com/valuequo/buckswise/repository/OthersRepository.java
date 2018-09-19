package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Others;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Others entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OthersRepository extends JpaRepository<Others, Long> {

}
