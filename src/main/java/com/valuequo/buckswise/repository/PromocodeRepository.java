package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Promocode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Promocode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {

}
