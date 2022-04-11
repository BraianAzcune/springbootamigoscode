package com.amigoscode.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    // private int vecesLlamado = 0;
    // public List<Student> getStudents() {
    // vecesLlamado++;
    // System.out.println("veces llamado: " + vecesLlamado);
    // return Arrays.asList(new Student(1L, "John", LocalDate.of(1990, 1, 1), 20),
    // new Student(2L, "Jane", LocalDate.of(1990, 1, 1), 20),
    // new Student(3L, "Jack", LocalDate.of(1990, 1, 1), 20),
    // new Student(4L, "Jill", LocalDate.of(1990, 1, 1), 20));
    // }
}
