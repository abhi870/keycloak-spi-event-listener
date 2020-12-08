package com.demo.login.provider;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.security.auth.Login;
import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import java.util.Properties;

import static com.demo.login.provider.Constants.*;
import static com.demo.login.provider.Constants.KAFKA_KEY_DESERIALIZER;

public class LogInEventListenerProviderFactory implements EventListenerProviderFactory {

    private LogInEventListenerProvider provider = null;
    private static final Logger LOG = Logger.getLogger(LogInEventListenerProvider.class);
    private KafkaProducer kafkaProducer = null;

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        if (provider == null) {
            provider = new LogInEventListenerProvider(kafkaProducer);
            LOG.info("LogInProviderFactory: loginprovider initiated...");
        }
        return provider;
    }

    @Override
    public void init(Config.Scope scope) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_BOOTSTRAP_SERVERS);
        props.put("acks", KAFKA_ACKS);
        props.put("retries", KAFKA_RETRIES);
        props.put("batch.size", KAFKA_BATCH_SIZE);
        props.put("linger.ms", KAFKA_LINGER_MS);
        props.put("buffer.memory", KAFKA_BUFFER_MEMORY);
        props.put("key.serializer", KAFKA_KEY_SERIALIZER);
        props.put("value.serializer", KAFKA_KEY_DESERIALIZER);
        kafkaProducer = new KafkaProducer<String, String>(props);
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "log_in_event_listener";
    }
}
