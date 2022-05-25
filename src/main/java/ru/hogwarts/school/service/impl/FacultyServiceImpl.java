package ru.hogwarts.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFacultyById(long facultyId) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return getFaculty(facultyId);
    }

    @Override
    public List<Student> getFacultyStudents(long facultyId) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        Faculty faculty = getFaculty(facultyId);
        return faculty.getStudents();
    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> getFindNameOrColor(String nameOrColor) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return facultyRepository.findFacultyByColorOrNameAllIgnoreCase(nameOrColor, nameOrColor);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long facultyId) {
        logger.info("Был вызван метод с названием: {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        facultyRepository.deleteById(facultyId);
    }

    private Faculty getFaculty(long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(
                () -> new NotFoundException("Факультет не найден!")
        );
    }
}
