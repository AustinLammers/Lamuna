package com.lamuna.Lamuna.entries;

import java.util.Iterator;

public abstract class FoodEntryComponent extends Entry implements Iterable<FoodEntryComponent> {
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

    public void remove(FoodEntryComponent workoutEntryComponent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<FoodEntryComponent> iterator() {
        return new FoodIterator();
    }

    public boolean hasChildren() {
        return false;
    }

    class FoodIterator implements Iterator<FoodEntryComponent> {

        boolean hasBeenRead = false;
        FoodIterator() {};
        @Override
        public boolean hasNext() {
            return !hasBeenRead;
        }

        @Override
        public FoodEntryComponent next() {
            hasBeenRead = true;
            return FoodEntryComponent.this;
        }
    }

}
