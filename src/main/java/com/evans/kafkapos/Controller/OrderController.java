package com.evans.kafkapos.Controller;

import com.evans.kafkapos.Entity.Order;
import com.evans.kafkapos.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/orders")
    public class OrderController {
        @Autowired
        private OrderService orderService;

        @GetMapping
        public List<Order> getAllOrders() {
            return orderService.getAllOrders();
        }

        @PostMapping
        public Order addOrder(@RequestBody Order order) {
            return orderService.addOrder(order);
        }

        @PutMapping("/{id}")
        public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
            return orderService.updateOrder(id, order);
        }

        @DeleteMapping("/{id}")
        public void deleteOrder(@PathVariable Long id) {
            orderService.deleteOrder(id);
        }
}
