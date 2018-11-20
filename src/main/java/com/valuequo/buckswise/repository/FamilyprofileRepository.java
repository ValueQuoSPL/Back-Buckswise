package com.valuequo.buckswise.repository;

import com.valuequo.buckswise.domain.Familyprofile;
import com.valuequo.buckswise.domain.User;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Familyprofile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FamilyprofileRepository extends JpaRepository<Familyprofile, Long> {
List<Familyprofile> findByUid(Long uid);
List<Familyprofile> findById(Long id);
}
