package com.mikhailkarpov.todos;

import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TodoService {

  private final TodoRepository todoRepository;
  private final ApplicationEventPublisher eventPublisher;

  public TodoService(TodoRepository todoRepository, ApplicationEventPublisher eventPublisher) {
    this.todoRepository = todoRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  public TodoEntity createTodo(String todo) {
    TodoEntity entity = todoRepository.save(new TodoEntity(todo));
    eventPublisher.publishEvent(TodoEvent.created(entity));
    return entity;
  }

  @Transactional(readOnly = true)
  public List<TodoEntity> findAllTodos() {
    return todoRepository.findAll();
  }

  @Transactional
  public void deleteTodo(Long id) {
    TodoEntity todo = todoRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found")
    );
    todoRepository.delete(todo);
    eventPublisher.publishEvent(TodoEvent.deleted(todo));
  }

}
