package com.evans.kafkapos.Controller;

import com.evans.kafkapos.Entity.Payment;
import com.evans.kafkapos.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/payments")
    public class PaymentController {

        @Autowired
        private PaymentService paymentService;

        @GetMapping
        public List<Payment> getAllPayments() {
            return paymentService.getAllPayments();
        }

        @PostMapping
        public Payment addPayment(@RequestBody Payment payment) {
            return paymentService.addPayment(payment);
        }
}
