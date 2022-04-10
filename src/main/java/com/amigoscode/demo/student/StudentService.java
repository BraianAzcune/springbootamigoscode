package com.amigoscode.demo.student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class StudentService {
    public List<Student> getStudents() {
        return Arrays.asList(new Student(1L, "John", LocalDate.of(1990, 1, 1), 20),
                new Student(2L, "Jane", LocalDate.of(1990, 1, 1), 20),
                new Student(3L, "Jack", LocalDate.of(1990, 1, 1), 20),
                new Student(4L, "Jill", LocalDate.of(1990, 1, 1), 20));
    }
}
