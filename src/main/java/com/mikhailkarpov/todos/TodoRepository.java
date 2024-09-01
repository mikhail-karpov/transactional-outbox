package com.mikhailkarpov.todos;

import org.springframework.data.repository.ListCrudRepository;

public interface TodoRepository extends ListCrudRepository<TodoEntity, Long> {


}
