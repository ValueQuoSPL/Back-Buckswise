package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.valuequo.buckswise.domain.GoalSet;

@SuppressWarnings("unused")
@Repository
public interface GoalSetRepository extends JpaRepository<GoalSet, Long> {
	List<GoalSet> findByUid(Long uid);
	List<GoalSet> findById(Long id);
}
