package com.mikhailkarpov.config;

import com.mikhailkarpov.outbox.OutboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class OutboxSchedulingConfig {

  private static final Logger LOG = LoggerFactory.getLogger(OutboxSchedulingConfig.class);

  @Autowired
  private OutboxService outboxService;

  @Scheduled(cron = "${app.outbox.scheduler-cron:0 * * * * *}")
  void sendOutbox() {
    outboxService.send(outbox -> LOG.info("Pretending to send outbox: {}", outbox));
  }
}
