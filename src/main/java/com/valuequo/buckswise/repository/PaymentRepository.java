package com.valuequo.buckswise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.valuequo.buckswise.domain.Payment;
import com.valuequo.buckswise.model.PaymentVM;

public interface PaymentRepository extends JpaRepository<Payment, Long>{


}
