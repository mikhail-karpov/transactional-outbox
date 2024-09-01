package com.mikhailkarpov.config;

import com.mikhailkarpov.outbox.OutboxSendingStrategy;
import com.mikhailkarpov.outbox.OutboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class OutboxSchedulingConfig {

  @Autowired
  private OutboxService outboxService;

  @Autowired
  private OutboxSendingStrategy outboxSendingStrategy;

  @Scheduled(cron = "${app.outbox.scheduler-cron:0 * * * * *}")
  void sendOutbox() {
    outboxService.send(outboxSendingStrategy);
  }
}
