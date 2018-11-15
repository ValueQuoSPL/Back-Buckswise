package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Entermntandtravel;

@SuppressWarnings("unused")
@Repository
public interface Entermntandtravelrepository extends JpaRepository<Entermntandtravel, Long>{

	List<Entermntandtravel> findByUserid(Long userid);

	@Query("select a from Entermntandtravel a where a.userid = :userid AND a.name = :name")
	List<Entermntandtravel> findByName(@Param("name") String name, @Param("userid") Long userid);

}
