package com.mikhailkarpov.outbox;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OutboxService {

  private static final Logger LOG = LoggerFactory.getLogger(OutboxService.class);

  private final OutboxRepository outboxRepository;

  public OutboxService(OutboxRepository outboxRepository) {
    this.outboxRepository = outboxRepository;
  }

  @Transactional
  public void save(Outbox outbox) {
      outboxRepository.save(new OutboxEntity(
          outbox.aggregateId(),
          outbox.eventType(),
          outbox.payload()
      ));
  }

  @Transactional
  public void send(OutboxSendingStrategy strategy) {
    List<OutboxEntity> toDeleteList = new LinkedList<>();
    for (OutboxEntity outboxEntity : outboxRepository.findAll()) {
      try {
        strategy.send(new Outbox(
            outboxEntity.getAggregateId(),
            outboxEntity.getEventType(),
            outboxEntity.getPayload())
        );
        toDeleteList.addFirst(outboxEntity);
      } catch (Exception e) {
        LOG.error("Failed to send {} due to {}", outboxEntity.getId(), e.getMessage());
      }
    }
    if (!toDeleteList.isEmpty()) {
      outboxRepository.deleteAllInBatch(toDeleteList);
    }
  }
}
