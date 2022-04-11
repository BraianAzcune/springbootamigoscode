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
            studentRepository.save(new Student("John", LocalDate.of(1997, 1, 1), "john@gmail.com"));
            studentRepository.save(new Student("Jane", LocalDate.of(2000, 1, 1), "Jane@gmail.com"));
            studentRepository.save(new Student("Jack", LocalDate.of(2010, 1, 1), "Jack@gmail.com"));
            studentRepository.save(new Student("Jill", LocalDate.of(1999, 1, 1), "Jill@gmail.com"));
        };
    }
}
