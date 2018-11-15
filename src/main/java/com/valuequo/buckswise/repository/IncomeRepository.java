package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Income;

@SuppressWarnings("unused")
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{

	List<Income> findByUserid(Long userid);
	
	@Query("select a from Income a where a.userid = :userid AND a.name = :name")
	List<Income> findByName(@Param("name") String name, @Param("userid") Long userid);

}
