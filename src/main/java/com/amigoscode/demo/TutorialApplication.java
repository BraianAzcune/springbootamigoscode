package com.amigoscode.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.amigoscode.demo.student.Student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/hellos")
	public List<String> hellos() {
		return Arrays.asList("Hello World", "Hola mundo", "Bonjour le monde", "Hallo Welt", "Ciao mondo");
	}

	@GetMapping("/students")
	public List<Student> students() {
		return Arrays.asList(new Student(1L, "John", LocalDate.of(1990, 1, 1), 20),
				new Student(2L, "Jane", LocalDate.of(1990, 1, 1), 20),
				new Student(3L, "Jack", LocalDate.of(1990, 1, 1), 20),
				new Student(4L, "Jill", LocalDate.of(1990, 1, 1), 20));
	}

}
