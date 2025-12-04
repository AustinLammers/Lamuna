package com.lamuna.Lamuna.entries;

public abstract class FoodEntryComponent extends Entry {
    private double protein;
    private double carbs;
    private double fat;

    public FoodEntryComponent(String name, String description, int calories) {
        super(name, description, calories);
        protein = 0;
        carbs = 0;
        fat = 0;
    }

    public FoodEntryComponent(String name, String description, int calories, double protein, double carbs, double fat) {
        super(name, description, calories);
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public FoodEntryComponent() {
        super();
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


    public void add(FoodEntryComponent workoutEntry){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(FoodEntryComponent workoutEntryComponent){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
