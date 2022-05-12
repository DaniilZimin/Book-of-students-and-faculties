package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(long studentId);

    Faculty getStudentFacultyById(long id);

    List<Student> getAllStudentsByAge(int age);

    List<Student> getFindByAgeBetween(int min, int max);

    Student updateStudent(Student student);

    void deleteStudent(long studentId);
}
