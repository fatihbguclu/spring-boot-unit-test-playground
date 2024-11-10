package org.ft;

import org.ft.model.CollegeStudent;
import org.ft.model.GradebookCollegeStudent;
import org.ft.repository.StudentDao;
import org.ft.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
public class GradebookControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentAndGradeService studentCreateServiceMock;

    @Autowired
    private StudentDao studentDao;

    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();
        request.setParameter("firstname", "Chad");
        request.setParameter("lastname", "Darby");
        request.setParameter("emailAddress", "chad.darby@luv2code_school.com");
    }

    @BeforeEach
    public void beforeEach() {
        jdbc.execute("insert into student(id, firstname, lastname, email_address) " + "values (1, 'Eric', 'Roby', 'eric.roby@luv2code_school.com')");
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute("DELETE FROM student");
    }

    @Test
    public void getStudentsHttpRequest() throws Exception{
        CollegeStudent studentOne = new GradebookCollegeStudent("Eric", "Roby", "eric_roby@luv2code_school.com");
        CollegeStudent studentTwo = new GradebookCollegeStudent("Chad", "Darby", "chad_darby@luv2code_school.com");
        List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(studentOne,studentTwo));

        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudentList);
        assertIterableEquals(collegeStudentList, studentCreateServiceMock.getGradeBook());

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");
    }

    @Test
    public void createStudentHttpRequest() throws Exception {
        CollegeStudent studentOne = new CollegeStudent("Eric",
                "Roby", "eric_roby@luv2code_school.com");
        List<CollegeStudent> collegeStudentList = new ArrayList<>(Arrays.asList(studentOne));
        when(studentCreateServiceMock.getGradeBook()).thenReturn(collegeStudentList);

        assertIterableEquals(collegeStudentList, studentCreateServiceMock.getGradeBook());

        MvcResult mvcResult = mockMvc
                .perform(post("/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .param("firstname", request.getParameterValues("firstname"))
                    .param("lastname", request.getParameterValues("lastname"))
                    .param("emailAddress", request.getParameterValues("emailAddress")))
                .andExpect(status().isOk())
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(modelAndView, "index");
        CollegeStudent verifyStudent = studentDao.findByEmailAddress("chad.darby@luv2code_school.com");
        assertNotNull(verifyStudent, "Student should be found");
    }

    @Test
    public void deleteStudentHttpRequest() throws Exception {
        assertTrue(studentDao.findById(1).isPresent());
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/delete/student/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "index");
        assertFalse(studentDao.findById(1).isPresent());
    }

    @Test
    public void deleteStudentHttpRequestErrorPage() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/delete/student/{id}", 0))
                .andExpect(status().isOk())
                .andReturn();
        ModelAndView modelAndView = mvcResult.getModelAndView();
        ModelAndViewAssert.assertViewName(modelAndView, "error");
    }
}
