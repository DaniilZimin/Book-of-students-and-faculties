package ru.hogwarts.school;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.AvatarController;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.service.impl.FacultyServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class FacultyControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarController avatarController;

    @MockBean
    private StudentController studentController;

    @MockBean
    private Faculty faculty;

    @MockBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFacultyTest() throws Exception {
        long id = 1L;
        String name = "Грифендор";
        String color = "Синий";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyService.createFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyByIdTest() throws Exception {
        long id = 1L;
        String name = "Грифендор";
        String color = "Синий";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyService.getFacultyById(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void getFacultyStudentsTest() throws Exception {
        long id = 1L;
        String name = "Грифендор";
        String color = "Синий";
        List<Student> students = List.of(new Student(1L, "Иван", 28),
                new Student(2L, "Николай", 29));

        Faculty facultyCopy = new Faculty();
        facultyCopy.setId(id);
        facultyCopy.setName(name);
        facultyCopy.setColor(color);
        facultyCopy.setStudents(students);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, facultyCopy);

        when(facultyService.getFacultyStudents(any(Long.class))).thenReturn(students);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(facultyCopy));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students?facultyId=1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Иван"))
                .andExpect(jsonPath("$[1].age").value("29"));
    }

    @Test
    public void getFacultyByColorTest() throws Exception {
        long id1 = 1L;
        long id2 = 2L;
        String name1 = "Грифендор";
        String name2 = "Слизерин";
        String color = "Синий";

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColor(color);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name1, faculty1);
        facultyObject.put(name2, faculty2);

        List<Faculty> faculties = List.of(faculty1, faculty2);

        when(facultyService.getFacultyByColor(any(String.class))).thenReturn(faculties);
        when(facultyRepository.findByColor(any(String.class))).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter/Синий")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Грифендор"))
                .andExpect(jsonPath("$[0].color").value("Синий"))
                .andExpect(jsonPath("$[1].name").value("Слизерин"))
                .andExpect(jsonPath("$[1].color").value("Синий"));
    }

    @Test
    public void getFindNameOrColorTest() throws Exception {
        long id1 = 1L;
        long id2 = 2L;
        String name1 = "Грифендор";
        String name2 = "Слизерин";
        String color = "Синий";

        Faculty faculty1 = new Faculty();
        faculty1.setId(id1);
        faculty1.setName(name1);
        faculty1.setColor(color);

        Faculty faculty2 = new Faculty();
        faculty2.setId(id2);
        faculty2.setName(name2);
        faculty2.setColor(color);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name1, faculty1);
        facultyObject.put(name2, faculty2);

        List<Faculty> faculties = List.of(faculty1, faculty2);
        List<Faculty> faculties1 = List.of(faculty1);

        when(facultyService.getFindNameOrColor("Синий")).thenReturn(faculties);
        when(facultyService.getFindNameOrColor("Грифендор")).thenReturn(faculties1);
        when(facultyRepository.findFacultyByColorOrNameAllIgnoreCase(color, color)).thenReturn(faculties);
        when(facultyRepository.findFacultyByColorOrNameAllIgnoreCase(name1, name1)).thenReturn(faculties1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findNameOrColor?nameOrColor=Синий")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Грифендор"))
                .andExpect(jsonPath("$[0].color").value("Синий"))
                .andExpect(jsonPath("$[1].name").value("Слизерин"))
                .andExpect(jsonPath("$[1].color").value("Синий"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findNameOrColor?nameOrColor=Грифендор")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Грифендор"))
                .andExpect(jsonPath("$[0].color").value("Синий"))
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    public void updateFacultyTest() throws Exception {
        long id = 1L;
        String name = "Грифендор";
        String color = "Синий";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.updateFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        long id = 1L;
        String name = "Грифендор";
        String color = "Синий";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put(name, id);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyService.updateFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}