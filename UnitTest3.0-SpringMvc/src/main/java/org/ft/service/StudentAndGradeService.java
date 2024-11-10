package org.ft.service;

import org.ft.model.CollegeStudent;
import org.ft.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    public void createStudent(String firstname, String lastname, String emailAddress) {
        CollegeStudent student = new CollegeStudent(firstname, lastname, emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> studentOptional = studentDao.findById(id);
        return studentOptional.isPresent();
    }

    public void deleteStudent(int id) {
        studentDao.deleteById(id);
    }
}
