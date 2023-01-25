package org.almirsantos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Component
public class OrderProducer {

    //Pegando o topic armazenado no arquivo application.properties
    @Value("${order.topic}")
    private String orderTopic;

    private final KafkaTemplate kafkaTemplate;

    //Construtor criado - não foi possível com @Autowired por causa do final (ver linha 17)
    public OrderProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final @RequestBody String order) {
        final String messageKey = UUID.randomUUID().toString();  //Não é obrigatório, mas estamos passando
        kafkaTemplate.send(orderTopic, messageKey, order);
    }

}
