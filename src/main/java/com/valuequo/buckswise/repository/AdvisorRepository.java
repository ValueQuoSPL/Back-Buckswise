package com.valuequo.buckswise.repository;

import java.util.List;

import com.valuequo.buckswise.domain.Advisor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data JPA repository for the Advisor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {

    @Query("select a from Advisor a where a.aid =:aid AND a.uid =:uid AND a.recotype =:type")
    List<Advisor> findByAid(@Param ("aid") Long aid, @Param ("uid") Long uid, @Param ("type") String type);

    // @Query("update Advisor a set a.usercomment =:comment and a.status")
    // void update(@Param("id") Long id, @Param("comment") Long comment, @Param("approveValue") String approveValue, @Param("rejectValue") String rejectValue);
}
