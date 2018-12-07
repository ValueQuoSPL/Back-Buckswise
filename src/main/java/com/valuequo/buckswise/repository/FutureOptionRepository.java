package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.FutureOption;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the FutureOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FutureOptionRepository extends JpaRepository<FutureOption, Long> {

	List<FutureOption> findByUserid(Long userid);

	FutureOption findById(Long id);

}
