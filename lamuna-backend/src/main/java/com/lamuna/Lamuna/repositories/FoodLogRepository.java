package com.lamuna.Lamuna.repositories;

import com.lamuna.Lamuna.entities.FoodLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodLogRepository extends JpaRepository<FoodLogEntity, Long> {}