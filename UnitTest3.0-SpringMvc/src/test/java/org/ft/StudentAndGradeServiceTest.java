package org.ft;

import org.ft.model.CollegeStudent;
import org.ft.model.HistoryGrade;
import org.ft.model.MathGrade;
import org.ft.model.ScienceGrade;
import org.ft.repository.HistoryGradeDao;
import org.ft.repository.MathGradeDao;
import org.ft.repository.ScienceGradeDao;
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
import java.util.Collection;
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
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setupDatabase() {
        jdbcTemplate.execute("insert into student(id, firstname, lastname, email_address)" + "values (1, 'Eric', 'Roby', 'eric.roby@luv2code_school.com')");
        jdbcTemplate.execute("insert into math_grade(id,student_id,grade) values (1,1,100.00)");
        jdbcTemplate.execute("insert into science_grade(id,student_id,grade) values (1,1,100.00)");
        jdbcTemplate.execute("insert into history_grade(id,student_id,grade) values (1,1,100.00)");
    }

    @AfterEach
    public void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM student");
        jdbcTemplate.execute("DELETE FROM math_grade");
        jdbcTemplate.execute("DELETE FROM science_grade");
        jdbcTemplate.execute("DELETE FROM history_grade");
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
        assertTrue(studentAndGradeService.createGrade(80.50, 1, "science"));
        assertTrue(studentAndGradeService.createGrade(80.50, 1, "history"));

        Iterable<MathGrade> mathGrades = mathGradeDao.findMathGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findScienceGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findHistoryGradeByStudentId(1);

        assertEquals(2, ((Collection<MathGrade>) mathGrades).size(), "Student has math grades");
        assertEquals(2, ((Collection<ScienceGrade>) scienceGrades).size(), "Student has science grades");
        assertEquals(2, ((Collection<HistoryGrade>) historyGrades).size(), "Student has history grades");
    }

    @Test
    public void createGradeServiceReturnFalse() {
        assertFalse(studentAndGradeService.createGrade(105, 1, "math"));
        assertFalse(studentAndGradeService.createGrade(-5, 1, "math"));
        assertFalse(studentAndGradeService.createGrade(80.50, 2, "math"));
        assertFalse(studentAndGradeService.createGrade(80.50, 1, "literature"));
    }

    @Test
    public void deleteGradeService() {
        assertEquals(1, studentAndGradeService.deleteGrade(1, "math"),
                "Returns student id after delete");
        assertEquals(1, studentAndGradeService.deleteGrade(1, "science"),
                "Returns student id after delete");
        assertEquals(1, studentAndGradeService.deleteGrade(1, "history"),
                "Returns student id after delete");
    }

    @Test
    public void deleteGradeServiceReturnStudentIdOfZero() {
        assertEquals(0, studentAndGradeService.deleteGrade(0, "science"),
                "No student should have 0 id");
        assertEquals(0, studentAndGradeService.deleteGrade(1, "literature"),
                "No student should have a literature class");
    }
}
