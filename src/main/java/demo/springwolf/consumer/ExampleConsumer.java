package demo.springwolf.consumer;

import demo.springwolf.model.AnotherPayload;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import demo.springwolf.model.ExamplePayload;

@Service
public class ExampleConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ExampleConsumer.class);

    @KafkaListener(topics = "example-topic", groupId = "example-group-id")
    public void receiveExamplePayload(ExamplePayload payload) {
        logger.info("Received new message in example-topic: {}", payload.toString());
    }

    @KafkaListener(topics = "another-topic", groupId = "example-group-id")
    public void receiveAnotherPayload(AnotherPayload payload) {
        logger.info("Received new message in another-topic: {}", payload.toString());
    }

    @AsyncListener(operation = @AsyncOperation(
            channelName = "example-manual-consumer-topic",
            description = "Optional. Customer uploaded an example payload"
    ))
    @KafkaAsyncOperationBinding
    public void nonAnnotatedReceiveExamplePayload(ExamplePayload payload) {
        logger.info("Received new message in example-topic: {}", payload.toString());
    }

}