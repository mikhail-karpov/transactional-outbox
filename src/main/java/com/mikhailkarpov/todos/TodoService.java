package com.mikhailkarpov.todos;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @Transactional
  public TodoEntity createTodo(String todo) {
    return todoRepository.save(new TodoEntity(todo));
  }

  @Transactional(readOnly = true)
  public List<TodoEntity> findAllTodos() {
    return todoRepository.findAll();
  }

  public void deleteTodo(Long id) {
    todoRepository.deleteById(id);
  }

}
