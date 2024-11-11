package org.ft;

import org.ft.model.CollegeStudent;
import org.ft.model.MathGrade;
import org.ft.repository.MathGradeDao;
import org.ft.repository.StudentDao;
import org.ft.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentAndGradeService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setupDatabase() {
        jdbcTemplate.execute("insert into student(id, firstname, lastname, email_address)" +
                "values (1, 'Eric', 'Roby', 'eric.roby@luv2code_school.com')");
    }

    @AfterEach
    public void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM student");
    }

    @Test
    public void createStudentTest() {
        studentAndGradeService.createStudent("Chad", "Darby", "chad.darby@luv2code_school.com");
        CollegeStudent collegeStudent = studentDao.findByEmailAddress("chad.darby@luv2code_school.com");
        assertEquals("chad.darby@luv2code_school.com", collegeStudent.getEmailAddress(), "find student by email");
    }

    @Test
    public void isStudentNullCheck() {
        assertTrue(studentAndGradeService.checkIfStudentIsNull(1));
        assertFalse(studentAndGradeService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentTest() {
        Optional<CollegeStudent> studentOptional = studentDao.findById(1);
        assertTrue(studentOptional.isPresent(), "Return True");

        studentAndGradeService.deleteStudent(1);

        Optional<CollegeStudent> deletedStudentOptional = studentDao.findById(1);
        assertFalse(deletedStudentOptional.isPresent(), "Return False");
    }

    @Sql("/insertData.sql")
    @Test
    public void getGradeBookService() {
        Iterable<CollegeStudent> gradeBook = studentAndGradeService.getGradeBook();
        List<CollegeStudent> collegeStudents = new ArrayList<>();
        gradeBook.forEach(collegeStudents::add);
        assertEquals(5, collegeStudents.size());
    }

    @Test
    public void createGradeService() {
        assertTrue(studentAndGradeService.createGrade(80.50, 1, "math"));
        Iterable<MathGrade> mathGrades = mathGradeDao.findMathGradeByStudentId(1);
        assertTrue(mathGrades.iterator().hasNext(), "Student has math grades");
    }
}
