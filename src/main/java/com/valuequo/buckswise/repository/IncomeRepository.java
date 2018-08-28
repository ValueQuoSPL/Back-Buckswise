package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Income;

@SuppressWarnings("unused")
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{

	List<Income> findByUserid(int userid);

	List<Income> findByName(String name);

}
