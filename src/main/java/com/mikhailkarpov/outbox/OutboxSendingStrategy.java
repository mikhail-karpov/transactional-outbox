package com.mikhailkarpov.outbox;

import java.util.concurrent.CompletableFuture;

public interface OutboxSendingStrategy {

  CompletableFuture<Void> send(Outbox outbox);

}
