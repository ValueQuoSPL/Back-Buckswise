package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Entermntandtravel;

@SuppressWarnings("unused")
@Repository
public interface Entermntandtravelrepository extends JpaRepository<Entermntandtravel, Long>{

	List<Entermntandtravel> findByUserid(int userid);

}
