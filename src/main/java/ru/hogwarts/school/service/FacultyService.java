package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> facultyByColor = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (faculty.getColor().equals(color)) {
                facultyByColor.add(faculty);
            }
        }
        return facultyByColor;
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }
}
