package com.lamuna.Lamuna.dto;

import com.lamuna.Lamuna.entries.WorkoutType;

import java.time.LocalDate;

public class CreateWorkoutLogRequest {
    private String name;
    private String description;
    private LocalDate date;
    private Integer calories;

    private Integer sets;

    private Integer reps;

    private Integer minutes;

    private Long userId;

    private WorkoutType type;

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getSets() {
        return sets;
    }

    public Integer getReps() {
        return reps;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public WorkoutType getType(){return type;}

    public Long getUserId() {
        return userId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public void setType(WorkoutType type) {this.type = type;}

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
