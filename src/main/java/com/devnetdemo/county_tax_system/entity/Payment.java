package com.devnetdemo.county_tax_system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "property_id")
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
