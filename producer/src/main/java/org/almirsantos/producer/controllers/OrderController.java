package org.almirsantos.producer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.almirsantos.producer.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/orders")
@Slf4j
public class OrderController {

    private final OrderProducer orderProducer;

    //Construtor criado - não foi possível com @Autowired por causa do final (ver linha 13)
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public void send(@RequestBody String order) {
        orderProducer.send(order);
    }

}
