package me.leoyakubov.todoapp.config;

import lombok.extern.slf4j.Slf4j;
import me.leoyakubov.todoapp.model.Todo;
import me.leoyakubov.todoapp.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.stream.Stream;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TodoRepository repository) {
        return args -> {
            Random rd = new Random();
            Stream.of("Buy milk", "Eat pizza", "Update tutorial", "Study Vue", "Go kayaking").forEach(name -> {
                Todo todo = new Todo();
                todo.setTitle(name);
                todo.setCompleted(rd.nextBoolean());
                repository.save(todo);
                log.info("Preloading " + todo);
            });
        };
    }
}
