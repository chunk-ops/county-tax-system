package com.devnetdemo.county_tax_system.service;

import com.devnetdemo.county_tax_system.entity.Property;
import com.devnetdemo.county_tax_system.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Optional<Property> getByParcelId(String parcelId) {
        return propertyRepository.findByParcelId(parcelId);
    }

    public BigDecimal calculateAnnualTax(Property property) {
        return property.getAssessedValue()
                .multiply(property.getTaxRate());
    }
}
