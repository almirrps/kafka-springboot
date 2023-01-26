# spring-boot-kafka

## Pré requisitos

- Maven 3+
- Java 8+
- Docker 18.02.0+ 

## Preparando ambiente

- Execute o `docker-compose up` para inicializar o Zookeeper e Kafka.
- Execute `mvn clean package` na pasta do projeto para realizar o build das aplicações.

## Executando 

- Inicialize o projeto `producer`
````
cd producer
mvn spring-boot:run
````

Obs: a aplicação Producer disponibiliza o endpoint `POST http://localhost:8080/orders` para receber os eventos dos pedidos.


-  Inicialize o projeto `consumer`
````
cd consumer
mvn spring-boot:run
````
Obs: O projeto do consumer não tem endpoint, ele apenas conecta no tópico do Kafka para escutar o stream.


## Executando 


Para testar, pode ser utilizado o seguinte comando: `./send-order.sh "{\"identifier\": \"12343\",\"customer\": \"Customer X\", \"value\": 1500}"`, onde será inserido o pedido no tópico do Kafka, via a aplicação `producer`, e será cosumido pela aplicação `consumer`, como no log abaixo:
````
2023-01-26 16:21:05.019  INFO 2103 --- [ntainer#0-0-C-1] b.c.almirsantos.consumer.OrderConsumer  : Order: Order(identifier=12343, customer=Customer X, value=1500)
````


Log da aplicação Consumer com mapeamento de mensagens "tipadas":
````
2023-01-26 17:36:00.546  INFO 4532 --- [ntainer#0-0-C-1] o.s.k.l.KafkaMessageListenerContainer    : partitions assigned: [ordertopic-0]
2023-01-26 17:41:23.413  INFO 4532 --- [ntainer#0-0-C-1] org.almirsantos.consumer.OrderConsumer   : key: 9d1fa0d8-94ce-4d71-a5fd-d54360625b73
2023-01-26 17:41:23.415  INFO 4532 --- [ntainer#0-0-C-1] org.almirsantos.consumer.OrderConsumer   : Headers: RecordHeaders(headers = [], isReadOnly = false)
2023-01-26 17:41:23.415  INFO 4532 --- [ntainer#0-0-C-1] org.almirsantos.consumer.OrderConsumer   : Partion: 0
2023-01-26 17:41:23.416  INFO 4532 --- [ntainer#0-0-C-1] org.almirsantos.consumer.OrderConsumer   : Order: {
    "identifier": "12343",
    "customer": "Customer X",
    "value": 1500
}
````
