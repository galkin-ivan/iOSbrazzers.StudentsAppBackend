package ru.galkinivan.StudentsAppBackend.dao;

import ru.galkinivan.StudentsAppBackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
