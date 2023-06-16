package com.payment.domain.services;

import com.payment.domain.helper.IDGenerator;
import com.payment.domain.model.entities.PaymentType;
import com.payment.domain.repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public PaymentTypeService(PaymentTypeRepository paymentTypeRepository) {
        this.paymentTypeRepository = paymentTypeRepository;
    }

    public List<PaymentType> getAllPaymentTypes() {
        return paymentTypeRepository.findAll();
    }

    public PaymentType getPaymentTypeById(String id) {
        Optional<PaymentType> optionalPaymentType = paymentTypeRepository.findById(id);
        return optionalPaymentType.orElse(null);
    }

    public PaymentType createPaymentType(PaymentType paymentType) {
        paymentType.setId(IDGenerator.generateID());
        return paymentTypeRepository.save(paymentType);
    }

    public PaymentType updatePaymentType(String id, PaymentType paymentType) {
        Optional<PaymentType> optionalPaymentType = paymentTypeRepository.findById(id);
        if (optionalPaymentType.isPresent()) {
            paymentType.setId(id);
            return paymentTypeRepository.save(paymentType);
        } else {
            return null;
        }
    }

    public boolean deletePaymentType(String id) {
        Optional<PaymentType> optionalPaymentType = paymentTypeRepository.findById(id);
        if (optionalPaymentType.isPresent()) {
            paymentTypeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
