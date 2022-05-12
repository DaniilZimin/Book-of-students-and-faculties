package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(long facultyId) {
        return getFaculty(facultyId);
    }

    @Override
    public List<Student> getFacultyStudents(long facultyId) {
        Faculty faculty = getFaculty(facultyId);
        return faculty.getStudents();
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> getFindNameOrColor(String nameOrColor) {
        return facultyRepository.findFacultyByColorOrNameAllIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    private Faculty getFaculty(long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(
                () -> new NotFoundException("Факультет не найден!")
        );
    }
}
