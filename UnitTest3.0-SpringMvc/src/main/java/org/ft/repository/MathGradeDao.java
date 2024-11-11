package org.ft.repository;

import org.ft.model.MathGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathGradeDao extends CrudRepository<MathGrade, Integer> {
    Iterable<MathGrade> findMathGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
