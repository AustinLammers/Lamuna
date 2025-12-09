package com.lamuna.Lamuna.controllers;

import com.lamuna.Lamuna.dto.CreateWorkoutLogRequest;
import com.lamuna.Lamuna.dto.WorkoutLogResponse;
import com.lamuna.Lamuna.services.WorkoutLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/workouts")
@RestController
public class WorkoutLogController {
    WorkoutLogService workoutLogService;

    public WorkoutLogController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    @GetMapping
    public List<WorkoutLogResponse> getAllWorkoutRows(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return workoutLogService.getAllWorkoutRows(date);
    }

    @PostMapping
    public WorkoutLogResponse createWorkoutRow(@RequestBody CreateWorkoutLogRequest workoutRequest) {
        return workoutLogService.create(workoutRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkoutRowRow(@PathVariable Long id) {
        workoutLogService.delete(id);
    }
}
