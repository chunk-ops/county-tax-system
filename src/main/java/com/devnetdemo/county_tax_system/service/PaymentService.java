package com.devnetdemo.county_tax_system.service;

import com.devnetdemo.county_tax_system.entity.Payment;
import com.devnetdemo.county_tax_system.entity.Property;
import com.devnetdemo.county_tax_system.repository.PaymentRepository;
import com.devnetdemo.county_tax_system.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PropertyRepository propertyRepository;

    public PaymentService(PaymentRepository paymentRepository, PropertyRepository propertyRepository) {
        this.paymentRepository = paymentRepository;
        this.propertyRepository = propertyRepository;
    }

    //record a new payment
    public Payment recordPayment(Payment payment) {

        if (payment.getProperty() == null || payment.getProperty().getParcelId() == null) {
            throw new IllegalArgumentException("Payment must include parcel id");
        }

        String parcelId = payment.getProperty().getParcelId();

        Property property = propertyRepository.findByParcelId(parcelId).orElseThrow(() -> new IllegalArgumentException("Property not found"));

        payment.setProperty(property);

        return paymentRepository.save(payment);
    }

    //get all payments for a property by parcelId
    public List<Payment> getPaymentsForProperty(String parcelId) {
        return paymentRepository.findByProperty_ParcelId(parcelId);
    }
    /*
    public List<Payment> getPaymentsForProperty(String parcelId) {
        Optional<Property> propertyOpt = propertyRepository.findByParcelId(parcelId);
        if (propertyOpt.isEmpty()) {
            return Collections.emptyList();
        }

        Property property = propertyOpt.get();
        return paymentRepository.findByProperty(property);
    }
    */

    public BigDecimal calculateTotalPayments(List<Payment> payments) {
        return payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    /*
    public BigDecimal calculateTotalPayments(Property property) {
        List<Payment> payments = paymentRepository.findByProperty(property);

        return payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
     */

    public BigDecimal calculateTotalPaymentsByParcelId(String parcelId) {
        List<Payment> payments = paymentRepository.findByProperty_ParcelId(parcelId);

        return payments.stream().map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
