package com.devnetdemo.county_tax_system.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String parcelId;

    private String ownerName;
    private String address;

    private BigDecimal assessedValue;
    private BigDecimal taxRate;

    private Integer taxYear;

    //getters and setters
    public Long getId() {return id;}

    public String getParcelId() {return parcelId;}
    public void setParcelId(String parcelId) {this.parcelId = parcelId;}

    public String getOwnerName() {return ownerName;}
    public void setOwnerName(String ownerName) {this.ownerName = ownerName;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public BigDecimal getAssessedValue() {return assessedValue;}
    public void setAssessedValue(BigDecimal assessedValue) {this.assessedValue = assessedValue;}

    public BigDecimal getTaxRate() {return taxRate;}
    public void setTaxRate(BigDecimal taxRate) {this.taxRate = taxRate;}

    public Integer getTaxYear() {return taxYear;}
    public void setTaxYear(Integer taxYear) {this.taxYear = taxYear;}
}
