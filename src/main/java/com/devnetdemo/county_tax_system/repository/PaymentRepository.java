package com.devnetdemo.county_tax_system.repository;

import com.devnetdemo.county_tax_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByPropertyId(Long propertyId);
}
