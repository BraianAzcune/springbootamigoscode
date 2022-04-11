package com.amigoscode.demo.student;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;

    private String name;
    private LocalDate dob;
    private Integer age;

    // Hibernate requiere un default constructor, el mismo utiliza reflexion y
    // setters para establecer los datos.
    public Student() {
    }

    public Student(Long id, String name, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    public Student(String name, LocalDate dob, Integer age) {
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [age=" + age + ", dob=" + dob + ", id=" + id + ", name=" + name + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
