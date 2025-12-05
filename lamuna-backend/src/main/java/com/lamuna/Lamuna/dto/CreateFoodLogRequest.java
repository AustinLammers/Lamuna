package com.lamuna.Lamuna.dto;

import java.time.LocalDate;

public class CreateFoodLogRequest {
    private String name;
    private String description;
    private LocalDate date;
    private Integer calories;
    private Double protein;
    private Double carbs;
    private Double fat;
    private Long userId;
    private Long parentLogId;

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

    public Double getProtein() {
        return protein;
    }

    public Double getCarbs() {
        return carbs;
    }

    public Double getFat() {
        return fat;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getParentLogId() {
        return parentLogId;
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

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setParentLogId(Long parentLogId) {
        this.parentLogId = parentLogId;
    }
}
