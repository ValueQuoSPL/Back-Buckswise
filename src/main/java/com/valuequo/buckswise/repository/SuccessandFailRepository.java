package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.SuccessandFailtransaction;

@SuppressWarnings("unused")
@Repository
public interface SuccessandFailRepository extends JpaRepository<SuccessandFailtransaction, Long> {

	List<SuccessandFailtransaction> findByUserid(Long userid);

	SuccessandFailtransaction findById(Long id);

}
