package com.payment.domain.controllers;

import com.payment.domain.model.entities.PaymentType;
import com.payment.domain.services.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-types")
public class PaymentTypeController {

    private final PaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentType>> getAllPaymentTypes() {
        List<PaymentType> paymentTypes = paymentTypeService.getAllPaymentTypes();
        return ResponseEntity.ok(paymentTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentType> getPaymentTypeById(@PathVariable("id") String id) {
        PaymentType paymentType = paymentTypeService.getPaymentTypeById(id);
        if (paymentType != null) {
            return ResponseEntity.ok(paymentType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PaymentType> createPaymentType(@RequestBody PaymentType paymentType) {
        PaymentType createdPaymentType = paymentTypeService.createPaymentType(paymentType);
        return new ResponseEntity<>(createdPaymentType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentType> updatePaymentType(@PathVariable("id") String id, @RequestBody PaymentType paymentType) {
        PaymentType updatedPaymentType = paymentTypeService.updatePaymentType(id, paymentType);
        if (updatedPaymentType != null) {
            return ResponseEntity.ok(updatedPaymentType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentType(@PathVariable("id") String id) {
        boolean deleted = paymentTypeService.deletePaymentType(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
