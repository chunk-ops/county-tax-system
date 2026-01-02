package com.devnetdemo.county_tax_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    public Payment() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;
    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @NotNull(message = "Property reference is required")
    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    private Property property;

    //getters and setters
    public Long getId() {return id;}

    public BigDecimal getAmount() {return amount;}
    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public LocalDate getPaymentDate() {return paymentDate;}
    public void setPaymentDate(LocalDate paymentDate) {this.paymentDate = paymentDate;}

    public Property getProperty() {return property;}
    public void setProperty(Property property) {this.property = property;}
}
