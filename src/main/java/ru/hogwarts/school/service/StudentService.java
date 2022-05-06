package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;

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
        return getStudent(studentId);
    }

    public Faculty getStudentFacultyById(long id) {
        return getStudent(id).getFaculty();
    }

    public List<Student> getAllStudentsByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    public List<Student> getFindByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    private Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Студент не найден!")
        );
    }
}
