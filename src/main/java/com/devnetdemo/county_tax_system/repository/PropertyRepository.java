package com.devnetdemo.county_tax_system.repository;

import com.devnetdemo.county_tax_system.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByParcelId(String parcelId);
}
