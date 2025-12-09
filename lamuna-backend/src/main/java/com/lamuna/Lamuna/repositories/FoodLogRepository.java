package com.lamuna.Lamuna.repositories;

import com.lamuna.Lamuna.entities.FoodLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodLogRepository extends JpaRepository<FoodLogEntity, Long> {
    List<FoodLogEntity> findAllByDate(LocalDate date);
}
