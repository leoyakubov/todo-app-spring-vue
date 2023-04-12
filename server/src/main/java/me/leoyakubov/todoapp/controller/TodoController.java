package me.leoyakubov.todoapp.controller;

import me.leoyakubov.todoapp.exception.TodoNotFoundException;
import me.leoyakubov.todoapp.model.Todo;
import me.leoyakubov.todoapp.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/todo")
public class TodoController {
        private final TodoService service;

        TodoController(TodoService service) {
            this.service = service;
        }

    @GetMapping("/")
    public List<Todo> getAll() {
            return service.findAll();
        }

        @PostMapping("")
        public Todo create(@RequestBody Todo newTask) {
            return service.save(newTask);
        }

        @GetMapping("/{id}")
        public Todo getById(@PathVariable Long id) {
            return service.findById(id)
                    .orElseThrow(() -> new TodoNotFoundException(id));
        }

        @PutMapping("/{id}")
        Todo update(@RequestBody Todo todo, @PathVariable Long id) {
            return service.update(todo, id);
        }

        @DeleteMapping("/{id}")
        void delete(@PathVariable Long id) {
            service.deleteById(id);
        }
}
