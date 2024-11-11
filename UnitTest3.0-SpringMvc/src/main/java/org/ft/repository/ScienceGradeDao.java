package org.ft.repository;

import org.ft.model.ScienceGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceGradeDao extends CrudRepository<ScienceGrade, Integer> {

    Iterable<ScienceGrade> findScienceGradeByStudentId(int id);

    void deleteByStudentId(int id);
}
