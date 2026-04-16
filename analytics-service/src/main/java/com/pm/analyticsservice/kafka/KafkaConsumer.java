package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    // topics: the topic to listen to, //
    // groupId: the consumer group this listener belongs to
    public void consumeEvent(byte[] event){
        System.out.println("Received event: " + new String(event));
        // Since parseFrom() is trying to convert the byte array back to a PatientEvent object,
        // it can throw an InvalidProtocolBufferException
        // if the byte array is not in the expected format or if there are issues with the data.
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // ... perform some analytics logic with the patientEvent data
            log.info("Received PatientEvent from Kafka: {}", patientEvent);
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserializing PatientEvent from Kafka message: {}", e.getMessage());
        }
    }
}
