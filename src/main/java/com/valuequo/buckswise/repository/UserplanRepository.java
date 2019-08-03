package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Userplan;
import org.springframework.data.repository.query.Param;
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

// @Query("select DATE(a.expirt_date) as 'date/time', curdate(), datediff(a.expiry_date, curdate()) from userplan a where uid =:3")

    // @Query("select DATE(a.expiry_date) as 'date/time', curdate(), datediff(a.expiry_Date, curdate()) from userplan a where uid =:uid")
    // Long getDateDiff(@Param("uid") Long uid);
}
