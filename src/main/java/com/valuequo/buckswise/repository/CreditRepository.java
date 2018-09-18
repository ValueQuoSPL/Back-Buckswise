package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

	List<Credit> findByUserid(int userid);

	List<Credit> findById(Long id);

}
