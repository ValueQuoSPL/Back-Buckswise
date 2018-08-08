package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

	List<Insurance> findByUserid(int userid);

}
