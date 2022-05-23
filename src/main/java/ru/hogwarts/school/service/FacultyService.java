package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Faculty getFacultyById(long facultyId);

    List<Student> getFacultyStudents(long facultyId);

    List<Faculty> getFacultyByColor(String color);

    List<Faculty> getFindNameOrColor(String nameOrColor);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(long facultyId);
}
