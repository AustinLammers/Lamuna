package com.lamuna.Lamuna.controllers;

import com.lamuna.Lamuna.dto.CreateWorkoutLogRequest;
import com.lamuna.Lamuna.dto.WorkoutLogResponse;
import com.lamuna.Lamuna.services.WorkoutLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/workouts")
@RestController
public class WorkoutLogController {
    WorkoutLogService workoutLogService;

    public WorkoutLogController(WorkoutLogService foodLogService) {
        this.workoutLogService = foodLogService;
    }

    @GetMapping
    public List<WorkoutLogResponse> getAllFoodRows() {
        return workoutLogService.getAllWorkoutRows();
    }

    @PostMapping
    public WorkoutLogResponse createWorkoutRow(@RequestBody CreateWorkoutLogRequest foodRequest) {
        return workoutLogService.create(foodRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodRow(@PathVariable Long id) {
        workoutLogService.delete(id);
    }
}
