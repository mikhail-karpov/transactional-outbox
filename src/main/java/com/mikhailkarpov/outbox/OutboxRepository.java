package com.mikhailkarpov.outbox;

import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface OutboxRepository extends JpaRepository<OutboxEntity, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  List<OutboxEntity> findAll(Sort sort);

}
