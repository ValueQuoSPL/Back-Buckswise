package com.valuequo.buckswise.service.mapper;

import org.mapstruct.Mapper;

import com.valuequo.buckswise.domain.Payment;
import com.valuequo.buckswise.model.PaymentVM;

@Mapper(componentModel = "spring", uses = {})
public interface PaymentMapper extends EntityMapper<PaymentVM, Payment>{
	default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }

}
