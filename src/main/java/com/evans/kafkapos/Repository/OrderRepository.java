package com.evans.kafkapos.Repository;

import com.evans.kafkapos.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
