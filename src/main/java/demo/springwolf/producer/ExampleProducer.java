package demo.springwolf.producer;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import demo.springwolf.model.*;

@Component
public class ExampleProducer {

    @Autowired
    private KafkaTemplate<String, ExamplePayload> kafkaTemplate;

    @AsyncPublisher(
            operation = @AsyncOperation(
                    channelName = "example-manual-producer-topic",
                    description = "Optional. Customer uploaded an example payload"
            )
    )
    @KafkaAsyncOperationBinding
    public void sendMessage(ExamplePayload msg) {
        kafkaTemplate.send("example-producer-topic", msg);
    }

}