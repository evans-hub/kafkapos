package com.evans.kafkapos.Repository;

import com.evans.kafkapos.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
