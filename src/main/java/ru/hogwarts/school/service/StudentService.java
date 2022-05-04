package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(long studentId) {
        return studentRepository.findById(studentId).get();
    }

    public Collection<Student> getAllStudentsByAge(int age) {
        List<Student> studentsByAge = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            if (student.getAge() == age) {
                studentsByAge.add(student);
            }
        }
        return studentsByAge;

    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }
}
