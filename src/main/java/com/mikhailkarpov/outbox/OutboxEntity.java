package com.mikhailkarpov.outbox;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.Instant;
import java.util.Objects;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "outbox")
@DynamicUpdate
public class OutboxEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_seq")
  @SequenceGenerator(
      name = "outbox_seq",
      sequenceName = "outbox_seq",
      initialValue = 1000,
      allocationSize = 25
  )
  private Long id;

  private String aggregateId;

  private String eventType;

  @Column(columnDefinition = "jsonb")
  @JdbcTypeCode(SqlTypes.JSON)
  private JsonNode payload;

  @CreatedDate
  private Instant createdAt;

  @Version
  private Integer version;

  protected OutboxEntity() {
  }

  public OutboxEntity(String aggregateId, String eventType, JsonNode payload) {
    this.aggregateId = aggregateId;
    this.eventType = eventType;
    this.payload = payload;
  }

  public Long getId() {
    return id;
  }

  public String getAggregateId() {
    return aggregateId;
  }

  public String getEventType() {
    return eventType;
  }

  public JsonNode getPayload() {
    return payload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    OutboxEntity that = (OutboxEntity) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
