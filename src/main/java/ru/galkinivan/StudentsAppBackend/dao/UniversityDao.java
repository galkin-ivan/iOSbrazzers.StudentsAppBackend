package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.University;

public interface UniversityDao extends JpaRepository<University, Long> {

}
