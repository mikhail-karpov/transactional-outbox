package com.mikhailkarpov.outbox;

public interface OutboxSendingStrategy {

  void send(Outbox outbox);

}
