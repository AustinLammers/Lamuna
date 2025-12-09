package com.lamuna.Lamuna.entities;

import com.lamuna.Lamuna.entries.workout.WorkoutType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "workoutlogs")
public class WorkoutLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 200)
    private String description;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "workout_type", nullable = false)
    private WorkoutType type;

    @Column(name = "time")
    private Integer minutes;

    private Integer sets;

    private Integer reps;

    @Column(name = "calories_burnt")
    private Integer calories;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer carbs) {
        this.reps = carbs;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public WorkoutType getType(){return type;}

    public void setType(WorkoutType type) {this.type = type;}
}
