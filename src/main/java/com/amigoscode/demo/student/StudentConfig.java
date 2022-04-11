package com.amigoscode.demo.student;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// lo utilizamos para rellenar la tabla al inicio, ya que la jpa esta en modo destruir entre inicios.
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            studentRepository.save(new Student("John", LocalDate.of(1990, 1, 1), 20));
            studentRepository.save(new Student("Jane", LocalDate.of(1990, 1, 1), 20));
            studentRepository.save(new Student("Jack", LocalDate.of(1990, 1, 1), 20));
            studentRepository.save(new Student("Jill", LocalDate.of(1990, 1, 1), 20));
        };
    }
}