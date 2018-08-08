package com.valuequo.buckswise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valuequo.buckswise.domain.Miscelleonous;

@Repository
public interface MiscellenousRepository extends JpaRepository<Miscelleonous, Long>{

	List<Miscelleonous> findByUserid(int userid);

}
