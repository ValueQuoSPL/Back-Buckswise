package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Loananddebt;

@SuppressWarnings("unused")
@Repository
public interface LoananddebtRepository extends JpaRepository<Loananddebt, Long>{

	List<Loananddebt> findByUserid(int userid);

}
