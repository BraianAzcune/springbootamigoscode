package com.amigoscode.demo.student;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/hellos")
    public List<String> hellosManyLanguages() {
        return Arrays.asList("Hello World", "Hola mundo", "Bonjour le monde", "Hallo Welt", "Ciao mondo");
    }

    @GetMapping("/all")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
}
