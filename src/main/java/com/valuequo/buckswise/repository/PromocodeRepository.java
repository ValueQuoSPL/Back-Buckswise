package com.valuequo.buckswise.repository;

import java.util.List;
import java.util.Optional;

import com.valuequo.buckswise.domain.Promocode;
import com.valuequo.buckswise.service.dto.PromocodeDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Promocode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {

	void deleteById(Long id);

	Optional<PromocodeDTO> findById(Long id);

}
