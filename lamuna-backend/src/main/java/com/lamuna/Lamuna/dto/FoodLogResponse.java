package com.lamuna.Lamuna.dto;

import java.time.LocalDate;

public class FoodLogResponse extends CreateFoodLogRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
