package com.demo.login.provider;

public class Constants {
    public static final String KAFKA_ACKS = "0";
    public static final String KAFKA_RETRIES = "0";
    public static final String KAFKA_BATCH_SIZE = "3";
    public static final String KAFKA_LINGER_MS = "1";
    public static final String KAFKA_BUFFER_MEMORY = "33554432";
    public static final String KAFKA_KEY_SERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KAFKA_KEY_DESERIALIZER = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KAFKA_BOOTSTRAP_SERVERS = "localhost:9092";
}
