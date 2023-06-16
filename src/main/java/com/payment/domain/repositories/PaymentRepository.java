package com.payment.domain.repositories;

import com.payment.domain.model.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    Page<Payment> findByCustomerIdAndPaymentTypeTypeName(Long customerId, String typeName, Pageable pageable);
}
