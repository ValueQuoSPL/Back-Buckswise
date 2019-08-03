package com.valuequo.buckswise.service;

import com.valuequo.buckswise.domain.Userplan;
import com.valuequo.buckswise.repository.UserplanRepository;
import com.valuequo.buckswise.service.dto.UserplanDTO;
import com.valuequo.buckswise.service.mapper.UserplanMapper;

import org.hibernate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
/**
 * Service Implementation for managing Userplan.
 */
@Service
@Transactional
public class UserplanService {

    private final Logger log = LoggerFactory.getLogger(UserplanService.class);

    private final UserplanRepository userplanRepository;

    private final UserplanMapper userplanMapper;
    
    private SessionFactory hibernateFactory;
    
    Session session = null;
    
    Transaction tx;

    // UserplanService(EntityManagerFactory factory) {
    //     this.hibernateFactory = factory.unwrap(SessionFactory.class);
    // }

    public UserplanService(UserplanRepository userplanRepository, UserplanMapper userplanMapper, EntityManagerFactory factory) {
        this.userplanRepository = userplanRepository;
        this.userplanMapper = userplanMapper;
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    /**
     * Save a userplan.
     *
     * @param userplanDTO the entity to save
     * @return the persisted entity
     */
    public UserplanDTO save(UserplanDTO userplanDTO) {
        log.debug("Request to save Userplan : {}", userplanDTO);
        Userplan userplan = userplanMapper.toEntity(userplanDTO);
        userplan = userplanRepository.save(userplan);
        return userplanMapper.toDto(userplan);
    }

    /**
     * Get all the userplans.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserplanDTO> findAll() {
        log.debug("Request to get all Userplans");
        return userplanRepository.findAll().stream()
            .map(userplanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<Userplan> getUser(Long uid) {
        return userplanRepository.findByUid(uid);
    }

    public Long getUserPlan(Long uid) {
        session = this.hibernateFactory.openSession();
        tx = session.beginTransaction();
        // String sqlQuery = "select datediff(DATE(a.expiry_Date), curdate()) from userplan a where a.uid =" + uid;
        String sqlQuery = "select a from Userplan a where FUNCTION('DATEDIFF', '2019-08-09', '2019-08-02')";
        Query query = session.createQuery(sqlQuery);
        query.executeUpdate();
        System.out.println(query.getResultList());
        tx.commit();
        session.close();
        return null;
    }
    

}
