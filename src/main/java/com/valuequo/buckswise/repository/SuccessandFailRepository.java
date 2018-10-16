package com.valuequo.buckswise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.SuccessandFailtransaction;

@SuppressWarnings("unused")
@Repository
public interface SuccessandFailRepository extends JpaRepository<SuccessandFailtransaction, Long> {

}
