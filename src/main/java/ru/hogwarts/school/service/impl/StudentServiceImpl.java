package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public int sum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000_00)
                .reduce(0, Integer::sum);
    }

    @Override
    public Double avgAgeStudents() {

        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElseThrow(() -> new NotFoundException("Студентов не найдено!"));
    }

    @Override
    public List<String> getAllStudentStartNameA() {

        return studentRepository.findAll()
                .stream()
                .parallel()
                .map(Student::getName)
                .filter(a -> a.charAt(0) == 'А')
                .map(a -> a.toUpperCase(Locale.ROOT))
                .sorted()
                .toList();
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(long studentId) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return getStudent(studentId);
    }

    @Override
    public Faculty getStudentFacultyById(long id) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return getStudent(id).getFaculty();
    }

    @Override
    public List<Student> getAllStudentsByAge(int age) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.findStudentByAge(age);
    }

    @Override
    public List<Student> getFindByAgeBetween(int min, int max) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long studentId) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        studentRepository.deleteById(studentId);
    }

    @Override
    public int studentAmount() {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.studentAmount();
    }

    @Override
    public double avgAgeStudent() {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.avgAgeStudent();
    }

    @Override
    public List<Student> lastFiveStudent() {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return studentRepository.lastFiveStudent();
    }

    private Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Студент не найден!")
        );
    }
}
