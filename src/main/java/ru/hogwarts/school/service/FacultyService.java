package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long countIdFaculties = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++countIdFaculties);
        faculties.put(countIdFaculties, faculty);
        return faculty;
    }

    public Faculty getFacultyById(long facultyId) {
        return faculties.get(facultyId);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> facultyByColor = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (faculty.getColor().equals(color)) {
                facultyByColor.add(faculty);
            }
        }
        return facultyByColor;
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long facultyId) {
        return faculties.remove(facultyId);
    }
}
