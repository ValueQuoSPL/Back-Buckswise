package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valuequo.buckswise.domain.Utility;

public interface UtilityRepository extends JpaRepository<Utility, Long>{

	List<Utility> findByUserid(int userid);

}
