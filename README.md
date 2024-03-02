we need to add spring-kafka dependency to the pom.xml to support the kafka in spring boot

	<dependency>
			<groupId>org.springframework.kafka</groupId>
			<artifactId>spring-kafka</artifactId>
		</dependency>

  We need to add the configuraion for the producer and consumers in application.yaml file as 
  shown below
```
  server:
  port: 8085
  kafka:
    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
    consumer:
      bootstrap-servers: localhost:9092
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-serializer: org.apache.kafka.common.serialization.StringDeserializer
      group-id: myGroup
      auto-offset-reset: earliest

```
once we have configured the consumers and the producer with bootstrap-server,key-serializer,value-serializer for producer 
and  bootstrap-servers, key-deserializer,value-serializer,group-id auto-offset-reset we can create the producer to send mesaage to
kafka broker and consumer to consume the message from kafka broker.

If we don not have the topic already created in kafka cluster we can create the topic in a spring boot app with the help
of kafkaAdmin bean. In spring boot the KafkaAdmin bean is autoconfigured. Kafka Admin used the NewTopic bean to create the
new topic as shown below

```
@Configuration
public class TopicConfiguration {



    @Bean
    public NewTopic createTopic(){

        return TopicBuilder.name("myNewTopic").
                partitions(1).replicas(1).build();
    }

}
```
Once the topic is created we can create the KafkaProducer. We use a class called KafkaTemplate to send message to the topic as shown below
KafkaTemplate take the config information regarding the bootstrap-servers,key-serializer and value-serializer from application.yaml 

```

@Component
public class KafkaProducer {

 {

    private final KafkaTemplate<String,String> myKafkaTemplate;


    KafkaProducer (KafkaTemplate<String,String> kafkaTemplate){
        this.myKafkaTemplate=kafkaTemplate;

    }

    public void sendMessage(String message){

        myKafkaTemplate.send("myNewTopic",message);


    }

}
}
```

@KafkaListener annotation can be used to consume the message from the topic, Here in the annotaion we specify the topic name and the group-id


```
@Component
@Slf4j
public class KafkaConsumer {


    @KafkaListener(topics = "myNewTopic", id = "myGroup")
    public void consumeMessage(String message) {
        System.out.println(message);
    }

}

```

