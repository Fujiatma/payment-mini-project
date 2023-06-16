package com.payment.domain.controllers;

import com.payment.domain.model.entities.Payment;
import com.payment.domain.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment) {
        Payment createdPayment = paymentService.createPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String paymentId) {
        Optional<Payment> payment = paymentService.getPaymentById(paymentId);
        return payment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable String paymentId,
            @Valid @RequestBody Payment updatedPayment
    ) {
        Optional<Payment> existingPayment = paymentService.getPaymentById(paymentId);
        if (existingPayment.isPresent()) {
            updatedPayment.setId(paymentId);
            Payment savedPayment = paymentService.updatePayment(updatedPayment);
            return ResponseEntity.ok(savedPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable String paymentId) {
        Optional<Payment> existingPayment = paymentService.getPaymentById(paymentId);
        if (existingPayment.isPresent()) {
            paymentService.deletePayment(paymentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint untuk mencari dan menampilkan data pembayaran dengan filter dan paginasi
    @GetMapping
    public ResponseEntity<Page<Payment>> getPayments(
            @RequestParam(required = true) Long customerId, // Filter wajib: customer_id
            @RequestParam(required = true) String typeName, // Filter wajib: type_name
            @RequestParam(defaultValue = "0") int page, // Nomor halaman (default: 0)
            @RequestParam(defaultValue = "10") int size // Jumlah data per halaman (default: 10)
    ) {
        // Membuat objek Pageable untuk paginasi
        Pageable pageable = PageRequest.of(page, size);

        // Memanggil service untuk mengambil data pembayaran dengan filter dan paginasi
        Page<Payment> payments = paymentService.getPaymentsByFilter(customerId, typeName, pageable);

        return ResponseEntity.ok(payments);
    }
}
