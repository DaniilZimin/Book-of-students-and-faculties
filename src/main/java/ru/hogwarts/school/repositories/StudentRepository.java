package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByAgeBetween(int min, int max);

    List<Student> findStudentByAge(int age);

    @Query(value = "SELECT COUNT(id) FROM student", nativeQuery = true)
    int studentAmount();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    double avgAgeStudent();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> lastFiveStudent();
}
