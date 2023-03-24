package com.evans.kafkapos.Repository;

import com.evans.kafkapos.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
