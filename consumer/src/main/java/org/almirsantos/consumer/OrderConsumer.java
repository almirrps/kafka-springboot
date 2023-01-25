package org.almirsantos.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    //Método que gera o log simples
//    @KafkaListener(topics = "${order.topic}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consumer(String order) {
//        log.info("Order" + order);
//    }

    //Método que utiliza de um ConsumerRecord para gerar o log
    @KafkaListener(topics = "${order.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(ConsumerRecord consumerRecord) {
        log.info("key: " + consumerRecord.key());
        log.info("Headers: " + consumerRecord.headers());
        log.info("Partion: " + consumerRecord.partition());
        log.info("Order: " + consumerRecord.value());
    }

}
