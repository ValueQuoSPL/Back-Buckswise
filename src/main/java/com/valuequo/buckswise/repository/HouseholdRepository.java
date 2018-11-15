package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Household;

@SuppressWarnings("unused")
@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long>{

	List<Household> findByUserid(Long userid);

	@Query("select a from Household a where a.userid = :userid AND a.name = :name")
	List<Household> findByName(@Param("name") String name, @Param("userid") Long userid);

}
