package me.leoyakubov.todoapp.service;

import lombok.extern.slf4j.Slf4j;
import me.leoyakubov.todoapp.model.Todo;
import me.leoyakubov.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {
    private final TodoRepository repository;

    TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> findAll() {
        List<Todo> allTodos = repository.findAll();
        log.info("Found todos: {}", allTodos.size());
        return allTodos;
    }

    public Todo save(Todo todo) {
        Todo savedTodo = repository.save(todo);
        log.info("Saved todo: {}", savedTodo);
        return savedTodo;
    }

    public Optional<Todo> findById(Long id) {
        return repository.findById(id);
    }

    public Todo update(Todo todo, Long id) {
        return repository.findById(id)
                .map(updatedTodo -> {
                    updatedTodo.setTitle(todo.getTitle());
                    updatedTodo.setCompleted(todo.getCompleted());
                    log.info("Updated todo: {}", updatedTodo);
                    return repository.save(updatedTodo);
                })
                .orElseGet(() -> {
                    todo.setId(id);
                    log.info("Saving todo {} with id {}", todo);
                    return repository.save(todo);
                });
    }

    public void deleteById(Long id) {
        log.info("Deleting todo with id: {}", id);
        repository.deleteById(id);
    }
}
