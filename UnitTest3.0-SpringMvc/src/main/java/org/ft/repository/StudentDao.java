package org.ft.repository;

import org.ft.model.CollegeStudent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<CollegeStudent, Integer> {
    public CollegeStudent findByEmailAddress(String emailAddress);
}
