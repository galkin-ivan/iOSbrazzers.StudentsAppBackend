package ru.galkinivan.StudentsAppBackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.galkinivan.StudentsAppBackend.model.TimeTable;

public interface TimeTableDao extends JpaRepository<TimeTable, Long> {
}
