package org.ft;

import org.ft.model.CollegeStudent;
import org.ft.repository.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private StudentAndGradeService studentAndGradeService;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void createStudentService() {
        studentAndGradeService.createStudent("Chad", "Darby", "chad.darby@luv2code_school.com");
        CollegeStudent collegeStudent = studentDao.findByEmailAddress("chad.darby@luv2code_school.com");
        assertEquals("chad.darby@luv2code_school.com", collegeStudent.getEmailAddress(), "find student by email");
    }
}
