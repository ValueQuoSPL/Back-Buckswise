package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Cash;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Cash entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CashRepository extends JpaRepository<Cash, Long> {

	List<Cash> findByUserid(Long userid);

}
