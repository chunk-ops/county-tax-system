package com.devnetdemo.county_tax_system.controller;

import com.devnetdemo.county_tax_system.entity.Payment;
import com.devnetdemo.county_tax_system.entity.Property;
import com.devnetdemo.county_tax_system.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //post a payment
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Payment saved = paymentService.recordPayment(payment);
        return ResponseEntity.ok(saved);
    }

    //get payments for a property by parcelId
    @GetMapping("/property/{parcelId}")
    public ResponseEntity<List<Payment>> getPaymentsForProperty(@PathVariable String parcelId) {
        List<Payment> payments = paymentService.getPaymentsForProperty(parcelId);
        if (payments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payments);
    }

    //get total payments for a property by parcelId
    @GetMapping ("/property/{parcelId}/total")
    public ResponseEntity<BigDecimal> getTotalPaymentsForProperty(@PathVariable String parcelId) {
        List<Payment> payments = paymentService.getPaymentsForProperty(parcelId);
        BigDecimal total = paymentService.calculateTotalPayments(payments);
        return ResponseEntity.ok(total);
    }
}
