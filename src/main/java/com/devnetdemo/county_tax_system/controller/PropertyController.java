package com.devnetdemo.county_tax_system.controller;

import com.devnetdemo.county_tax_system.entity.Property;
import com.devnetdemo.county_tax_system.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        Property saved = propertyService.saveProperty(property);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{parcelId}")
    public ResponseEntity<?> getPropertyByParcelId(@PathVariable String parcelId) {

        Optional<Property> propertyOpt = propertyService.getByParcelId(parcelId);

        if (propertyOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Property property = propertyOpt.get();

        BigDecimal annualTax = propertyService.calculateAnnualTax(property);

        Map<String, Object> response = new HashMap<>();
        response.put("parcelId", property.getParcelId());
        response.put("ownerName", property.getOwnerName());
        response.put("address", property.getAddress());
        response.put("assessedValue", property.getAssessedValue());
        response.put("taxRate", property.getTaxRate());
        response.put("annualTax", annualTax);

        return ResponseEntity.ok(response);
    }
}
