package com.lamuna.Lamuna.services;

import com.lamuna.Lamuna.dto.CreateWorkoutLogRequest;
import com.lamuna.Lamuna.dto.WorkoutLogResponse;
import com.lamuna.Lamuna.entities.WorkoutLogEntity;
import com.lamuna.Lamuna.entries.WorkoutEntry;
import com.lamuna.Lamuna.entries.WorkoutEntryFactory;
import com.lamuna.Lamuna.entries.WorkoutType;
import com.lamuna.Lamuna.repositories.WorkoutLogRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkoutLogService {
    private final WorkoutLogRepository workoutLogRepository;
    private final WorkoutEntryFactory workoutEntryFactory;
    private static final Set<String> MEAL_NAMES = Set.of("Breakfast", "Lunch", "Dinner", "Snack", "Compound Ingredient");

    WorkoutLogService(WorkoutLogRepository workoutLogRepository, WorkoutEntryFactory workoutEntryFactory) {
        this.workoutLogRepository = workoutLogRepository;
        this.workoutEntryFactory = workoutEntryFactory;
    }

    public List<WorkoutLogResponse> getAllWorkoutRows() {
        List<WorkoutLogEntity> workoutRows = workoutLogRepository.findAll();

        List<WorkoutLogResponse> responses = new ArrayList<>();
        for (WorkoutLogEntity entity: workoutRows) {
            responses.add(toBasicResponse(entity));
        }

        return responses;
    }

    public List<WorkoutEntry> getAllWorkouts() {
        List<WorkoutLogEntity> workoutRows = workoutLogRepository.findAll();

        List<WorkoutEntry> responses = new ArrayList<>();
        for (WorkoutLogEntity entity: workoutRows) {
            responses.add(workoutEntryFactory.createWorkoutEntry(entity.getType(), entity.getName(), entity.getDescription(), entity.getCalories(), entity.getSets(), entity.getReps(), entity.getMinutes()));
        }

        return responses;
    }

    public WorkoutLogResponse create(CreateWorkoutLogRequest requestToCreateRow) {
        return toBasicResponse(setRequestFields(requestToCreateRow));
    }

    public void delete(Long id) {
        workoutLogRepository.deleteById(id);
    }

    // Helpers
    private WorkoutLogResponse toBasicResponse(WorkoutLogEntity workoutRow) {
        WorkoutLogResponse newResponseRow = new WorkoutLogResponse();

        newResponseRow.setId(workoutRow.getId());
        newResponseRow.setName(workoutRow.getName());
        newResponseRow.setDescription(workoutRow.getDescription());
        newResponseRow.setDate(workoutRow.getDate());
        newResponseRow.setUserId(workoutRow.getUserId());
        newResponseRow.setCalories(workoutRow.getCalories());
        newResponseRow.setMinutes(workoutRow.getMinutes());
        newResponseRow.setReps(workoutRow.getReps());
        newResponseRow.setSets(workoutRow.getSets());

        return newResponseRow;
    }

    private WorkoutLogEntity setRequestFields(CreateWorkoutLogRequest requestToCreateRow) {
        WorkoutLogEntity newRow = new WorkoutLogEntity();

        newRow.setName(requestToCreateRow.getName());
        newRow.setDescription(requestToCreateRow.getDescription());
        newRow.setDate(requestToCreateRow.getDate());
        newRow.setCalories(requestToCreateRow.getCalories());
        newRow.setReps(requestToCreateRow.getReps());
        newRow.setSets(requestToCreateRow.getSets());
        newRow.setMinutes(requestToCreateRow.getMinutes());
        newRow.setUserId(requestToCreateRow.getUserId());
        newRow.setType(requestToCreateRow.getType());

        return workoutLogRepository.save(newRow);
    }

    private int safeInt(Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    private double safeDouble(Double value) {
        if (value == null) {
            return 0.0;
        }
        return value;
    }
}
