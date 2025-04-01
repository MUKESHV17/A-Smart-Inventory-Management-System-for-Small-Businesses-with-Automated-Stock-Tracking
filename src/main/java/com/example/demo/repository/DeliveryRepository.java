package com.example.demo.repository;

import com.example.demo.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

   
    Page<Delivery> findByShipmentId(Long shipmentId, Pageable pageable);
}
