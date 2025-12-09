package com.lamuna.Lamuna.repositories;

import com.lamuna.Lamuna.entities.WorkoutLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLogEntity, Long> {
    List<WorkoutLogEntity> findAllByDate(LocalDate date);
}
