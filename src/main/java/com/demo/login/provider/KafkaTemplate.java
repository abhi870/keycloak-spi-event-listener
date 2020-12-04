package com.demo.login.provider;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

import static com.demo.login.provider.Constants.*;
import static com.demo.login.provider.Constants.KAFKA_KEY_DESERIALIZER;

public class KafkaTemplate {

    private Properties props = null;
    private KafkaProducer kafkaProducer = null;

    public KafkaTemplate() {
        this.setKafkaProps();
    }

    public KafkaProducer getKafkaProducer() {
        return kafkaProducer;
    }

    private Properties setKafkaProps() {
        props = new Properties();
        props.put("bootstrap.servers", KAFKA_BOOTSTRAP_SERVERS);
        props.put("acks", KAFKA_ACKS);
        props.put("retries", KAFKA_RETRIES);
        props.put("batch.size", KAFKA_BATCH_SIZE);
        props.put("linger.ms", KAFKA_LINGER_MS);
        props.put("buffer.memory", KAFKA_BUFFER_MEMORY);
        props.put("key.serializer", KAFKA_KEY_SERIALIZER);
        props.put("value.serializer", KAFKA_KEY_DESERIALIZER);
        kafkaProducer = new KafkaProducer<String, String>(props);
        return props;
    }

}
