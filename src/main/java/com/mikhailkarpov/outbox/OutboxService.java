package com.mikhailkarpov.outbox;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OutboxService {

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
    Sort sort = Sort.by(Direction.ASC, "createdAt");
    for (OutboxEntity outboxEntity : outboxRepository.findAll(sort)) {
        strategy.send(new Outbox(
            outboxEntity.getAggregateId(),
            outboxEntity.getEventType(),
            outboxEntity.getPayload())
        ).thenRun(() -> outboxRepository.deleteById(outboxEntity.getId()));
    }
  }
}
