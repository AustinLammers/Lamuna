package com.lamuna.Lamuna.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodLogResponse extends CreateFoodLogRequest {
    private Long id;
    private List<IngredientBreakdown> ingredients = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<IngredientBreakdown> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientBreakdown> ingredients) {
        this.ingredients = ingredients;
    }

    public static class IngredientBreakdown {
        private Long id;
        private String name;
        private String description;
        private Integer calories;
        private Double protein;
        private Double carbs;
        private Double fat;

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

        public Integer getCalories() {
            return calories;
        }

        public void setCalories(Integer calories) {
            this.calories = calories;
        }

        public Double getProtein() {
            return protein;
        }

        public void setProtein(Double protein) {
            this.protein = protein;
        }

        public Double getCarbs() {
            return carbs;
        }

        public void setCarbs(Double carbs) {
            this.carbs = carbs;
        }

        public Double getFat() {
            return fat;
        }

        public void setFat(Double fat) {
            this.fat = fat;
        }
    }
}
