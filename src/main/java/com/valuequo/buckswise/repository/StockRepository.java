package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Stock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	List<Stock> findByUserid(Long userid);

	Stock findById(Long id);

}
