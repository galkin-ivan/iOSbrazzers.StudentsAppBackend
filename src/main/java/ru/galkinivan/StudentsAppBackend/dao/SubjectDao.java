package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.Subject;

public interface SubjectDao extends JpaRepository<Subject, Long> {
}
