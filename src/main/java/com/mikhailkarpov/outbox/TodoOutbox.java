package com.mikhailkarpov.outbox;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikhailkarpov.todos.TodoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TodoOutbox {

  private final ObjectMapper objectMapper;
  private final OutboxService outboxService;

  public TodoOutbox(ObjectMapper objectMapper, OutboxService outboxService) {
    this.objectMapper = objectMapper;
    this.outboxService = outboxService;
  }

  @EventListener(TodoEvent.class)
  public void listen(TodoEvent event) {
    JsonNode jsonNode = objectMapper.convertValue(event, JsonNode.class);
    Outbox outbox = new Outbox(String.valueOf(event.id()), event.type().name(), jsonNode);
    outboxService.save(outbox);
  }

}
