package com.lamuna.Lamuna.entries;

import java.time.LocalDate;

public class FoodEntry extends Entry {
    private double protein;
    private double carbs;
    private double fat;
    public FoodEntry(String name, String description, int calories) {
        super(name, description, calories);

    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getProtein() {
        return protein;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
    public double getCarbs() {
        return carbs;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFat() {
        return fat;
    }
}
