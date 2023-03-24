package com.evans.kafkapos.Service;

import com.evans.kafkapos.Entity.Order;
import com.evans.kafkapos.Kafka.KafkaProducerService;
import com.evans.kafkapos.Repository.OrderRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

   /* public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setCustomerAddress(order.getCustomerAddress());
        existingOrder.setCustomerPhone(order.getCustomerPhone());
        existingOrder.setOrderItems(order.getOrderItems());
        existingOrder.setTotalPrice(order.getTotalPrice());
        return orderRepository.save(existingOrder);
    }*/

    public void deleteOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        orderRepository.delete(existingOrder);
    }

    public Order addOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        kafkaProducerService.sendMessage(savedOrder);
        return savedOrder;
    }

    public Order updateOrder(Long id, Order order) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        existingOrder.setCustomerName(order.getCustomerName());
        existingOrder.setCustomerAddress(order.getCustomerAddress());
        existingOrder.setCustomerPhone(order.getCustomerPhone());
        existingOrder.setOrderItems(order.getOrderItems());
        existingOrder.setTotalPrice(order.getTotalPrice());
        Order updatedOrder = orderRepository.save(existingOrder);
        kafkaProducerService.sendMessage(updatedOrder);
        return updatedOrder;
    }



}

