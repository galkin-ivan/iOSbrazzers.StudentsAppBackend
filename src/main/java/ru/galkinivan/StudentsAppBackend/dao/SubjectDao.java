package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.galkinivan.StudentsAppBackend.model.Subject;

public interface SubjectDao extends JpaRepository<Subject, Long> {

    @Query("select s from Subject s where s.name LIKE CONCAT('%',:name,'%')")
    Iterable<Subject> findByNameWithLike(@Param("name") String name);

}
