package com.example.demo.repository;

import com.example.demo.entity.Payment;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT p FROM Payment p WHERE p.amount = :amount")
    Optional<Payment>findByAmountOptional(@Param("amount")double amount);
}
