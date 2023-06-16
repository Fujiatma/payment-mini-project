package com.payment.domain.services;

import com.payment.domain.helper.IDGenerator;
import com.payment.domain.model.entities.Payment;
import com.payment.domain.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        payment.setId(IDGenerator.generateID());
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        payment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return paymentRepository.save(payment);
    }

    public Optional<Payment> getPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public Payment updatePayment(Payment payment) {
        payment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return paymentRepository.save(payment);
    }

    public void deletePayment(String paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    // Metode untuk mengambil data pembayaran dengan filter dan paginasi
    public Page<Payment> getPaymentsByFilter(Long customerId, String typeName, Pageable pageable) {
        // Memanggil repository untuk melakukan query dengan filter dan paginasi
        return paymentRepository.findByCustomerIdAndPaymentTypeTypeName(customerId, typeName, pageable);
    }
}
