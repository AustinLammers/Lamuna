package com.lamuna.Lamuna.dto;

public class WorkoutLogResponse extends CreateWorkoutLogRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
