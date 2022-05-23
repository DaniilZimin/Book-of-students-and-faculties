package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long studentId) {
        return getStudent(studentId);
    }

    @Override
    public Faculty getStudentFacultyById(long id) {
        return getStudent(id).getFaculty();
    }

    @Override
    public List<Student> getAllStudentsByAge(int age) {
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public List<Student> getFindByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public int studentAmount() {
        return studentRepository.studentAmount();
    }

    @Override
    public double avgAgeStudent() {
        return studentRepository.avgAgeStudent();
    }

    @Override
    public List<Student> lastFiveStudent() {
        return studentRepository.lastFiveStudent();
    }

    private Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Студент не найден!")
        );
    }
}
