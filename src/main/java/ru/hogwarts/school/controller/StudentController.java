package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.impl.StudentServiceImpl;

import java.util.Collection;
import java.util.List;

@Tag(name = "StudentController", description = "Контроллер по работе со студентами")
@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }


    @Operation(summary = "Сумма чисел", description = "Сумма чисел")
    @GetMapping("sum")
    public Integer sum() {
        return studentService.sum();
    }

    @Operation(summary = "Получение среднего возраста студентов", description = "Получение среднего возраста студентов")
    @GetMapping("getAvgAgeStudents")
    public ResponseEntity<Double> getAvgAgeStudents() {
        return ResponseEntity.ok(studentService.avgAgeStudents());
    }

    @Operation(summary = "Получение имени студента на букву А в верхнем регистре", description = "Получение имени студента на букву А в верхнем регистре")
    @GetMapping("getAllStudentStartNameA")
    public ResponseEntity<List<String>> getAllStudentStartNameA() {
        List<String> names = studentService.getAllStudentStartNameA();
        if (names.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(names);
    }

    @Operation(summary = "Создание студента", description = "Создание студента")
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @Operation(summary = "Получение студента по идентификатору", description = "Получение студента по идентификатору")
    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @Operation(summary = "Получение факультета студента", description = "Получение факультета студента")
    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable long id) {
        Faculty faculty = studentService.getStudentFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @Operation(summary = "Получение студентов по возрасту", description = "Получение студентов по возрасту")
    @GetMapping("/filter/{age}")
    public ResponseEntity<List<Student>> getAllStudentsByAge(@PathVariable int age) {
        List<Student> students = studentService.getAllStudentsByAge(age);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Получение студентов по промежутку возраста", description = "Получение студентов по промежутку возраста")
    @GetMapping("/finAgeGap")
    public ResponseEntity<Collection<Student>> getFindByAgeBetween(@RequestParam int min, @RequestParam int max) {
        Collection<Student> students = studentService.getFindByAgeBetween(min, max);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Изменение студента", description = "Изменение студента")
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @Operation(summary = "Удаление студента по идентефикатору", description = "Удаление студента по идентефикатору")
    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Количество всех студентов", description = "Количество всех студентов")
    @GetMapping("amountStudent")
    public Integer studentAmount() {
        return studentService.studentAmount();
    }

    @Operation(summary = "Средний возраст студентов", description = "Средний возраст студентов")
    @GetMapping("avgAgeStudent")
    public Double avgAgeStudent() {
        return studentService.avgAgeStudent();
    }

    @Operation(summary = "Последние пять студентов в списке", description = "Последние пять студентов в списке")
    @GetMapping("lastFiveStudent")
    public ResponseEntity<List<Student>> lastFiveStudent() {
        List<Student> lastFiveStudent = studentService.lastFiveStudent();
        if (lastFiveStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(lastFiveStudent);
    }
}
