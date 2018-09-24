package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Assetmapping;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Assetmapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetmappingRepository extends JpaRepository<Assetmapping, Long> {

}
