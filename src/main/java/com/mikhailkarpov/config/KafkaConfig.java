package com.mikhailkarpov.config;

import com.mikhailkarpov.kafka.KafkaOutboxSendingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration(proxyBeanMethods = false)
public class KafkaConfig {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @Value("${app.outbox.topic}")
  private String topic;

  @Bean
  KafkaOutboxSendingStrategy kafkaOutboxSendingStrategy() {
    return new KafkaOutboxSendingStrategy(topic, kafkaTemplate);
  }

}
