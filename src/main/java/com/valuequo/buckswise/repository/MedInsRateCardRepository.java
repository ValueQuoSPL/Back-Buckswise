package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.MedInsRateCard;

@SuppressWarnings("unused")
@Repository
public interface MedInsRateCardRepository extends JpaRepository<MedInsRateCard, Long>{
	
	@Query("select a from MedInsRateCard a where a.hospitaltype = :type AND a.roomtype = :room")
	List<MedInsRateCard> findOne(@Param("room")String roomType, @Param("type") String type);

}
