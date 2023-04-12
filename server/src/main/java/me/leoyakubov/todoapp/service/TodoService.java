package me.leoyakubov.todoapp.service;

import me.leoyakubov.todoapp.model.Todo;
import me.leoyakubov.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository repository;

    TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> findAll() {
        return repository.findAll();
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public Optional<Todo> findById(Long id) {
        return repository.findById(id);
    }

    public Todo update(Todo todo, Long id) {
        return repository.findById(id)
                .map(updatedTodo -> {
                    updatedTodo.setTitle(todo.getTitle());
                    updatedTodo.setCompleted(todo.getCompleted());
                return repository.save(updatedTodo);
                })
                .orElseGet(() -> {
                    todo.setId(id);
                    return repository.save(todo);
                });
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
