package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valuequo.buckswise.domain.Health;

public interface HealthRepository extends JpaRepository<Health, Long>{

	List<Health> findByUserid(int userid);

}
