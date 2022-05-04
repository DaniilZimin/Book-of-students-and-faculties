package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hogwarts.school.service.StudentService;

@SpringBootApplication
@OpenAPIDefinition
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
