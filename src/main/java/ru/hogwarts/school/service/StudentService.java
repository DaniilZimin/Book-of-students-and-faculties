package ru.hogwarts.school.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long countIdStudents = 0;

    public Student createStudent(Student student) {
        student.setId(++countIdStudents);
        students.put(countIdStudents, student);
        return student;
    }

    public Student getStudentById(long studentId) {
        return students.get(studentId);
    }

    public Collection<Student> getAllStudentsByAge(int age) {
        List<Student> studentsByAge = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                studentsByAge.add(student);
            }
        }
        return studentsByAge;
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
    }

    public Student deleteStudent(long studentId) {
        return students.remove(studentId);
    }
}
