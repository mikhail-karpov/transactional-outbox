package com.mikhailkarpov.todos;

public record TodoEvent(Long id, String todo, Type type) {

  public static TodoEvent created(TodoEntity todo) {
    return new TodoEvent(todo.getId(), todo.getTodo(), Type.CREATED);
  }

  public static TodoEvent deleted(TodoEntity todo) {
    return new TodoEvent(todo.getId(), todo.getTodo(), Type.DELETED);
  }

  public enum Type {
    CREATED, DELETED
  }

}
