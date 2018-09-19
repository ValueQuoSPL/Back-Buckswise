package com.valuequo.buckswise.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.valuequo.buckswise.domain.Userplan;
import com.valuequo.buckswise.service.dto.UserplanDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;



/**
 * Spring Data  repository for the Userplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserplanRepository extends JpaRepository<Userplan, Long> {

	void deleteById(Long id);

	Optional<UserplanDTO> findById(Long id);

	// Stream<Userplan> findById(Long id);

}
