package com.mikhailkarpov.todos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class TodoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todos_seq")
  @SequenceGenerator(
      name = "todos_seq",
      sequenceName = "todos_seq",
      initialValue = 1000,
      allocationSize = 25
  )
  private Long id;

  private String todo;

  protected TodoEntity() {
  }

  public TodoEntity(String todo) {
    this.todo = todo;
  }

  public Long getId() {
    return id;
  }

  public String getTodo() {
    return todo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TodoEntity that = (TodoEntity) o;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

}
