package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.Group;

public interface GroupDao extends JpaRepository<Group, Long> {
}
