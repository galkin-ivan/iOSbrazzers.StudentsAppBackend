package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.galkinivan.StudentsAppBackend.model.University;

@Repository
public interface UniversityDao extends JpaRepository<University, Long> {

    @Query("select b from University b where b.name = :name")
    University findByname(@Param("name") String name);
}
