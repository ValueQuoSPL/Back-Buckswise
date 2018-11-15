package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.valuequo.buckswise.domain.Utility;

public interface UtilityRepository extends JpaRepository<Utility, Long>{

	List<Utility> findByUserid(Long userid);

	@Query("select a from Utility a where a.userid = :userid AND a.name = :name")
	List<Utility> findByName(@Param("name") String name, @Param("userid") Long userid);

}
