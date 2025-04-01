package com.example.demo.service;

import com.example.demo.entity.Delivery;
import com.example.demo.entity.Shipment;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

   
    public Page<Delivery> getAllDeliveries(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return deliveryRepository.findAll(pageable);
    }

   
    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

   
    public Page<Delivery> getDeliveriesByShipmentId(Long shipmentId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return deliveryRepository.findByShipmentId(shipmentId, pageable);
    }

   
    public Delivery createDelivery(Long shipmentId, Delivery delivery) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id " + shipmentId));
        delivery.setShipment(shipment);
        return deliveryRepository.save(delivery);
    }

   
    public Delivery updateDelivery(Long id, Delivery deliveryDetails) {
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setRecipient(deliveryDetails.getRecipient());
            delivery.setOrigin(deliveryDetails.getOrigin());
            delivery.setDestination(deliveryDetails.getDestination());
            delivery.setDeliveryDate(deliveryDetails.getDeliveryDate());
            delivery.setStatus(deliveryDetails.getStatus());
            return deliveryRepository.save(delivery);
        }).orElseThrow(() -> new RuntimeException("Delivery not found with id " + id));
    }

  
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
