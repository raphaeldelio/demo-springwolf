# Springwolf Demo
Springwolf is an automated AsyncAPI documentation tool. It automatically detects annotations
in your Spring code, such as @KafkaListener, and generates an AsyncAPI document from them.

Besides that, it also provides a nice UI to view the generated documentation and to send example
messages to your message broker. 

In this demo, I try to configure Springwolf with generated Avro classes from the schema registry.

Even though I'm able to configure Springwolf to send messages to the Kafka Broker with the Schema Registry, 
the generated AsyncAPI message examples are not correct.

This is the configuration to produce messages with the Schema Registry with Springwolf:
```yaml
  plugin:
    kafka:
      publishing:
        enabled: true
        producer:
          bootstrap-servers: ${kafka.bootstrap.servers:localhost:29092}
          key-serializer: org.apache.kafka.common.serialization.StringSerializer
          value-serializer: io.confluent.kafka.serializers.KafkaAvroSerializer
          properties:
            schema:
              registry:
                url: http://localhost:8081
```

## Workflow
1. Define your Avro schema (.avsc files)
2. Generate Java classes from the schema
3. Implement Kafka producer and consumers
4. Run the application to access Springwolf UI on http://localhost:8080/springwolf/asyncapi-ui.html