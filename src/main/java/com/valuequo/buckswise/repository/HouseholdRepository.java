package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Household;

@SuppressWarnings("unused")
@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long>{

	List<Household> findByUserid(int userid);

}
