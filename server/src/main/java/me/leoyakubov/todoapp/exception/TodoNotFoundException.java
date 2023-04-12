package me.leoyakubov.todoapp.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
