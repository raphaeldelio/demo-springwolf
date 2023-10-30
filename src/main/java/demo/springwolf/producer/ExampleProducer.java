package demo.springwolf.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import demo.springwolf.model.*;

@Component
public class ExampleProducer {

    @Autowired
    private KafkaTemplate<String, ExamplePayload> kafkaTemplate;

    public void sendMessage(ExamplePayload msg) {
        kafkaTemplate.send("example-producer-topic", msg);
    }

}