package com.evans.kafkapos.Kafka;

import com.evans.kafkapos.Entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;


public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "order-topic", groupId = "group-id")
    public void consume(Order order) {
        logger.info("Consumed order: " + order.toString());
        // Perform some action based on the order
        System.out.println(" ===== ");
    }

}
