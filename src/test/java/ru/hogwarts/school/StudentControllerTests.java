package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testPostStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setAge(23);
        student.setName("Иван");

        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class)).isNotNull();
    }

    @Test
    void testGetStudentById() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/2", String.class)).contains("Сергей");
    }

    @Test
    void testGetFacultyStudent() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/2/faculty", String.class)).isNotNull();
    }

    @Test
    void testGetStudentByAge() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/filter/23", String.class)).isNotEmpty();
    }

    @Test
    void testGetStudentFinAgeGap() {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student/finAgeGap?min=19&max=27", List.class)).isNotEmpty();
    }

    @Test
    void testDeleteStudent() {
        assertDoesNotThrow(() -> this.testRestTemplate.delete("http://localhost:" + port + "/student/2"));
    }

    @Test
    void testPutStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setAge(23);
        student.setName("Иван");

        assertDoesNotThrow(() -> this.testRestTemplate.put("http://localhost:" + port + "/student/2", student));
    }
}
