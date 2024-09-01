package com.mikhailkarpov.kafka;


import com.fasterxml.jackson.databind.JsonNode;
import com.mikhailkarpov.outbox.Outbox;
import com.mikhailkarpov.outbox.OutboxSendingStrategy;
import java.util.concurrent.CompletableFuture;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaOutboxSendingStrategy implements OutboxSendingStrategy {

  private static final Logger LOG = LoggerFactory.getLogger(KafkaOutboxSendingStrategy.class);

  private final String topic;
  private final KafkaTemplate<String, Object> kafkaTemplate;

  public KafkaOutboxSendingStrategy(String topic, KafkaTemplate<String, Object> kafkaTemplate) {
    this.topic = topic;
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public CompletableFuture<Void> send(Outbox outbox) {
    String key = outbox.aggregateId();
    JsonNode payload = outbox.payload();
    return kafkaTemplate.send(new ProducerRecord<>(topic, key, payload))
        .thenAccept(sendResult -> LOG.info("{}", sendResult));
  }

}
