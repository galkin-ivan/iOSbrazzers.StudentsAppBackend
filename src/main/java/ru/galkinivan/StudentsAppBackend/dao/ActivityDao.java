package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.Activity;

public interface ActivityDao extends JpaRepository<Activity, Long>{
}
