package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.Faculty;

public interface FacultyDao extends JpaRepository<Faculty, Long> {

}
