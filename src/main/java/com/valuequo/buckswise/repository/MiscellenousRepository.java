package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Miscelleonous;

@Repository
public interface MiscellenousRepository extends JpaRepository<Miscelleonous, Long>{

	List<Miscelleonous> findByUserid(Long userid);

	@Query("select a from Miscelleonous a where a.userid = :userid AND a.name = :name")
	List<Miscelleonous> findByName(@Param("name") String name, @Param("userid") Long userid);
}
