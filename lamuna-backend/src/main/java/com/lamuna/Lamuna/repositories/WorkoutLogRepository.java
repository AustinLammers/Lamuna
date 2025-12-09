package com.lamuna.Lamuna.repositories;

import com.lamuna.Lamuna.entities.WorkoutLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutLogRepository extends JpaRepository<WorkoutLogEntity, Long> {}