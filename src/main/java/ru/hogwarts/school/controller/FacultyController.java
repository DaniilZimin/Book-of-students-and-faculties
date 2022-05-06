package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> create(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @Operation(summary = "Получение студентов факультета по идентификатору", description = "Получение студентов факультета по идентификатору")
    @GetMapping("students")
    public ResponseEntity<List<Student>> getFacultyStudents(@RequestParam("facultyId") long facultyId) {
        List<Student> students = facultyService.getFacultyStudents(facultyId);
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(students);
    }

    @Operation(summary = "Получение факультов по цвету факультета", description = "Получение факультетов по цвету факультета")
    @GetMapping("filter/{color}")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@PathVariable String color) {
        List<Faculty> faculties = facultyService.getFacultyByColor(color);
        if (faculties.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculties);
    }

    @Operation(summary = "Получение факультетов по названию или цвету факультета", description = "Получение факультетов по названию или цвету факультета")
    @GetMapping("/findNameOrColor")
    public ResponseEntity<List<Faculty>> getFindNameOrColor(@RequestParam String nameOrColor) {
        List<Faculty> faculties = facultyService.getFindNameOrColor(nameOrColor);
        if (faculties.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculties);
    }

    @Operation(summary = "Изменение факультета", description = "Изменение факультета")
    @PutMapping
    public ResponseEntity<Faculty> updateStudent(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @Operation(summary = "Удаление факультета по иденетефикатору", description = "Удаление факультета по иденетефикатору")
    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return ResponseEntity.ok().build();
    }
}
