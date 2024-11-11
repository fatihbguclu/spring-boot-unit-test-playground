package org.ft.repository;

import org.ft.model.ScienceGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceGradeDao extends CrudRepository<ScienceGrade, Integer> {

    public Iterable<ScienceGrade> findScienceGradeByStudentId(int id);

}
