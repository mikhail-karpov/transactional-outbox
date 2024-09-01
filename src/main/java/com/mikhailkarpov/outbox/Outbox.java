package com.mikhailkarpov.outbox;

import com.fasterxml.jackson.databind.JsonNode;

public record Outbox(String aggregateId, String eventType, JsonNode payload) {

}
