package com.demo.login.provider;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;

import java.util.Map;

public class LogInEventListenerProvider implements EventListenerProvider {

    private KafkaProducer kafkaProducer = null;

    public LogInEventListenerProvider(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void onEvent(Event event) {
        try {
            kafkaProducer.send(new ProducerRecord<String, String>("test", this.toString(event)));
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        try {
            kafkaProducer.send(new ProducerRecord<String, String>("test", this.toString(adminEvent)));
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public void close() {

    }

    private String toString(Event event) {

        StringBuilder sb = new StringBuilder();


        sb.append("type=");

        sb.append(event.getType());

        sb.append(", realmId=");

        sb.append(event.getRealmId());

        sb.append(", clientId=");

        sb.append(event.getClientId());

        sb.append(", userId=");

        sb.append(event.getUserId());

        sb.append(", ipAddress=");

        sb.append(event.getIpAddress());


        if (event.getError() != null) {

            sb.append(", error=");

            sb.append(event.getError());

        }


        if (event.getDetails() != null) {

            for (Map.Entry<String, String> e : event.getDetails().entrySet()) {

                sb.append(", ");

                sb.append(e.getKey());

                if (e.getValue() == null || e.getValue().indexOf(' ') == -1) {

                    sb.append("=");

                    sb.append(e.getValue());

                } else {

                    sb.append("='");

                    sb.append(e.getValue());

                    sb.append("'");

                }

            }

        }


        return sb.toString();

    }

    private String toString(AdminEvent adminEvent) {

        StringBuilder sb = new StringBuilder();


        sb.append("operationType=");

        sb.append(adminEvent.getOperationType());

        sb.append(", realmId=");

        sb.append(adminEvent.getAuthDetails().getRealmId());

        sb.append(", clientId=");

        sb.append(adminEvent.getAuthDetails().getClientId());

        sb.append(", userId=");

        sb.append(adminEvent.getAuthDetails().getUserId());

        sb.append(", ipAddress=");

        sb.append(adminEvent.getAuthDetails().getIpAddress());

        sb.append(", resourcePath=");

        sb.append(adminEvent.getResourcePath());


        if (adminEvent.getError() != null) {

            sb.append(", error=");

            sb.append(adminEvent.getError());

        }


        return sb.toString();

    }

}
