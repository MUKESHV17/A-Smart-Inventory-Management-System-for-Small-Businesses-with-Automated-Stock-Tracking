package com.example.demo.service;

import com.example.demo.entity.Shipment;
import com.example.demo.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

   
    public Page<Shipment> getAllShipments(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return shipmentRepository.findAll(pageable);
    }

   
    public Optional<Shipment> getShipmentById(Long id) {
        return shipmentRepository.findById(id);
    }

   
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    
    public Shipment updateShipment(Long id, Shipment shipmentDetails) {
        return shipmentRepository.findById(id).map(shipment -> {
            shipment.setOrigin(shipmentDetails.getOrigin());
            shipment.setDestination(shipmentDetails.getDestination());
            shipment.setShipmentDate(shipmentDetails.getShipmentDate());
            shipment.setEstimatedDeliveryDate(shipmentDetails.getEstimatedDeliveryDate());
            shipment.setStatus(shipmentDetails.getStatus());
            shipment.setWeight(shipmentDetails.getWeight());
            shipment.setVolume(shipmentDetails.getVolume());
            shipment.setCost(shipmentDetails.getCost());
            return shipmentRepository.save(shipment);
        }).orElseThrow(() -> new RuntimeException("Shipment not found with id " + id));
    }

   
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
