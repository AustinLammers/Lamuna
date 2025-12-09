package com.lamuna.Lamuna.services;

import com.lamuna.Lamuna.dto.CreateWorkoutLogRequest;
import com.lamuna.Lamuna.dto.WorkoutLogResponse;
import com.lamuna.Lamuna.entities.WorkoutLogEntity;
import com.lamuna.Lamuna.entries.workout.WorkoutEntry;
import com.lamuna.Lamuna.entries.workout.WorkoutEntryFactory;
import com.lamuna.Lamuna.repositories.WorkoutLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutLogService {
    private final WorkoutLogRepository workoutLogRepository;
    private final WorkoutEntryFactory workoutEntryFactory;

    WorkoutLogService(WorkoutLogRepository workoutLogRepository, WorkoutEntryFactory workoutEntryFactory) {
        this.workoutLogRepository = workoutLogRepository;
        this.workoutEntryFactory = workoutEntryFactory;
    }

    public List<WorkoutLogResponse> getAllWorkoutRows() {
        return getAllWorkoutRows(null);
    }

    public List<WorkoutLogResponse> getAllWorkoutRows(LocalDate date) {
        List<WorkoutLogEntity> workoutRows = (date == null)
                ? workoutLogRepository.findAll()
                : workoutLogRepository.findAllByDate(date);

        List<WorkoutLogResponse> responses = new ArrayList<>();
        for (WorkoutLogEntity entity: workoutRows) {
            WorkoutEntry entry = workoutEntryFactory.createWorkoutEntry(
                    entity.getType(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getCalories() == null ? 0 : entity.getCalories(),
                    entity.getSets() == null ? 0 : entity.getSets(),
                    entity.getReps() == null ? 0 : entity.getReps(),
                    entity.getMinutes() == null ? 0 : entity.getMinutes()
            );
            responses.add(toBasicResponse(entity, entry));
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
        WorkoutEntry entry = workoutEntryFactory.createWorkoutEntry(
                workoutRow.getType(),
                workoutRow.getName(),
                workoutRow.getDescription(),
                workoutRow.getCalories() == null ? 0 : workoutRow.getCalories(),
                workoutRow.getSets() == null ? 0 : workoutRow.getSets(),
                workoutRow.getReps() == null ? 0 : workoutRow.getReps(),
                workoutRow.getMinutes() == null ? 0 : workoutRow.getMinutes()
        );
        return toBasicResponse(workoutRow, entry);
    }

    private WorkoutLogResponse toBasicResponse(WorkoutLogEntity workoutRow, WorkoutEntry entry) {
        WorkoutLogResponse newResponseRow = new WorkoutLogResponse();

        newResponseRow.setId(workoutRow.getId());
        newResponseRow.setName(workoutRow.getName());
        newResponseRow.setDescription(workoutRow.getDescription());
        newResponseRow.setDate(workoutRow.getDate());
        newResponseRow.setUserId(workoutRow.getUserId());
        newResponseRow.setCalories(entry.getCalories());
        newResponseRow.setMinutes(entry.getMinutes());
        newResponseRow.setReps(entry.getReps());
        newResponseRow.setSets(entry.getSets());
        newResponseRow.setType(workoutRow.getType());

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
