package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Long> {
}
