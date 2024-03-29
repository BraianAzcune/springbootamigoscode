package com.amigoscode.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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

    public void registerStudent(Student student) {
        Optional<Student> op = this.studentRepository.findByEmail(student.getEmail());
        if (op.isPresent()) {
            throw new IllegalStateException("Email registered");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        this.studentRepository.findById(studentId).ifPresent(this.studentRepository::delete);
    }

    @Transactional
    public void updateStudent(Student student) {
        Optional<Student> op = this.studentRepository.findById(student.getId());
        if (op.isPresent()) {
            Student studentToUpdate = op.get();

            studentToUpdate.setName(student.getName());

            if (student.getEmail() != null && studentToUpdate.getEmail() != student.getEmail()
                    && this.studentRepository.findByEmail(student.getEmail()).isPresent()) {
                throw new IllegalStateException("Email registered");
            }

            BeanUtils.copyProperties(student, studentToUpdate, "id");
        }
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
