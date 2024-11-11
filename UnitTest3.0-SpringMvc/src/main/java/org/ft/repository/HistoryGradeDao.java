package org.ft.repository;

import org.ft.model.HistoryGrade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryGradeDao extends CrudRepository<HistoryGrade, Integer> {

    public Iterable<HistoryGrade> findHistoryGradeByStudentId(int id);

}
