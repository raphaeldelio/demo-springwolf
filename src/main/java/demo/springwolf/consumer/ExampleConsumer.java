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

import static org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

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
            channelName = "example-consumer-topic",
            description = "Optional. Customer uploaded an example payload",
            headers = @AsyncOperation.Headers(
                    schemaName = "SpringKafkaDefaultHeaders",
                    values = {
                            @AsyncOperation.Headers.Header(
                                    name = DEFAULT_CLASSID_FIELD_NAME,
                                    description = "Spring Type Id Header",
                                    value = "demo.springwolf.model.ExamplePayloadDto"
                            ),
                    }
            )
    ))
    @KafkaAsyncOperationBinding
    public void nonAnnotatedReceiveExamplePayload(ExamplePayload payload) {
        logger.info("Received new message in example-topic: {}", payload.toString());
    }

}