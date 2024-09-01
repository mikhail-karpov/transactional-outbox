package com.mikhailkarpov.todos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  private static TodoResponse mapEntityToResponse(TodoEntity todo) {
    return new TodoResponse(todo.getId(), todo.getTodo());
  }

  @PostMapping
  TodoResponse createTodo(@Valid @RequestBody CreateTodoRequest request) {
    TodoEntity todo = todoService.createTodo(request.todo);
    return mapEntityToResponse(todo);
  }

  @GetMapping
  List<TodoResponse> getTodos() {
    return todoService.findAllTodos().stream().map(TodoController::mapEntityToResponse).toList();
  }

  @DeleteMapping("/{id}")
  void deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
  }

  record CreateTodoRequest(@NotBlank @Size(max = 255) String todo) {

  }

  record TodoResponse(Long id, String todo) {

  }

}
