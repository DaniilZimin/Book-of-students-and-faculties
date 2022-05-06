package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

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
        return getFaculty(facultyId);
    }

    public List<Student> getFacultyStudents(long facultyId) {
        Faculty faculty = getFaculty(facultyId);
        return faculty.getStudents();
    }

    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> getFindNameOrColor(String nameOrColor) {
        return facultyRepository.findFacultyByColorOrNameAllIgnoreCase(nameOrColor, nameOrColor);
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    private Faculty getFaculty(long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(
                () -> new NotFoundException("Факультет не найден!")
        );
    }
}
