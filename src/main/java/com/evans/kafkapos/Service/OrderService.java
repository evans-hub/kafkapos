package com.evans.kafkapos.Service;

import com.evans.kafkapos.Entity.Order;
import com.evans.kafkapos.Entity.OrderItem;
import com.evans.kafkapos.Entity.Product;
import com.evans.kafkapos.Kafka.KafkaProducerService;
import com.evans.kafkapos.Repository.OrderRepository;
import com.evans.kafkapos.Repository.ProductRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) throws Exception {
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findByProductName(item.getProductName());
            if (product == null) {
                throw new Exception("Product not found");
            }
            if (product.getQuantity() < item.getQuantity()) {
                throw new Exception("Product " + product.getProductName() + " is out of stock");
            }
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }
        order.setTotalPrice(calculateTotalPrice(order.getOrderItems()));
        return orderRepository.save(order);
    }

    private BigDecimal calculateTotalPrice(List<OrderItem> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItem item : items) {
            totalPrice = totalPrice.add(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return totalPrice;
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
    }

    public void deleteOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
        orderRepository.delete(existingOrder);
    }

  /*  public Order addOrder(Order order) {
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
    }*/



}

