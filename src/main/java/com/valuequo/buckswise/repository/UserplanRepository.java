package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Userplan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Spring Data  repository for the Userplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserplanRepository extends JpaRepository<Userplan, Long> {
List<Userplan> findByUid(Long uid);
}
