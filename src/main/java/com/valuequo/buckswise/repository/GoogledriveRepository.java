package com.valuequo.buckswise.repository;

import java.util.List;

import com.valuequo.buckswise.domain.Googledrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogledriveRepository extends JpaRepository<Googledrive, Long> {

	List<Googledrive> findByUid(Long uid);

}