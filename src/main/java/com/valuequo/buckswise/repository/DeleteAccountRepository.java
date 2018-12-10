package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.DeleteAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unused")
@Repository
public interface DeleteAccountRepository extends JpaRepository<DeleteAccount, Long> {
    @Procedure(name = "userDetails")
	void deleteUserDetails(@Param("deluid") Long uid);
} 