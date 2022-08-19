package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
                .map(String::toUpperCase)
                .sorted()
                .toList();
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

    @Override
    public void printName() {
        List<Student> students = studentRepository.findAll();

        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    @Override
    public void printNameSynchronized() {

        run(0);
        run(1);

        new Thread(() -> {
            run(2);
            run(3);
        }).start();

        new Thread(() -> {
            run(4);
            run(5);
        }).start();
    }

    @Override
    public int sum() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000_00)
                .reduce(0, Integer::sum);
    }

    private synchronized void run(int id) {
        String students = studentRepository.findAll().get(id).getName();

        System.out.println(students);
    }

    private Student getStudent(long studentId) {
        return studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException("Студент не найден!")
        );
    }
}
