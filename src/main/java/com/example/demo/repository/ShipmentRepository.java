package com.example.demo.repository;

import com.example.demo.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    
    @SuppressWarnings("null")
    Page<Shipment> findAll(@SuppressWarnings("null") Pageable pageable);
}
 