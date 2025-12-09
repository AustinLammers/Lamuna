package com.lamuna.Lamuna.entries.food;

import com.lamuna.Lamuna.entries.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class FoodEntryComponent extends Entry implements Iterable<FoodEntryComponent> {
    private double protein;
    private double carbs;
    private double fat;

    FoodEntryComponent(String name, String description, int calories) {
        super(name, description, calories);
        protein = 0;
        carbs = 0;
        fat = 0;
    }

    FoodEntryComponent(String name, String description, int calories, double protein, double carbs, double fat) {
        super(name, description, calories);
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
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


    protected void add(FoodEntryComponent workoutEntry){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void remove(FoodEntryComponent workoutEntryComponent) {
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

    public static FoodEntryComponentBuilder getBuilder() {
        return new FoodEntryComponentBuilder();
    }

    public static class FoodEntryComponentBuilder {
        private List<FoodEntryComponent> children = new ArrayList<FoodEntryComponent>();
        private String name;
        private String description;
        private int calories;
        private double protein;
        private double carbs;
        private double fat;
        private boolean isComposite = false;

        public FoodEntryComponentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FoodEntryComponentBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FoodEntryComponentBuilder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public FoodEntryComponentBuilder protein(Double protein) {
            this.protein = protein;
            return this;
        }

        public FoodEntryComponentBuilder carbs(Double carbs) {
            this.carbs = carbs;
            return this;
        }

        public FoodEntryComponentBuilder fat(Double fat) {
            this.fat = fat;
            return this;
        }

        public FoodEntryComponentBuilder createAsMealItem() {
            isComposite = true;
            return this;
        }

        public FoodEntryComponentBuilder createAsIngredientItem() {
            isComposite = false;
            return this;
        }

        public FoodEntryComponentBuilder addIngredient(FoodEntryComponent component) {
            children.add(component);
            return this;
        }

        public FoodEntryComponent build() {
            if (isComposite) {
                if (children.isEmpty()) {
                    throw new IllegalStateException("A composite item must have at least one child");
                }

                FoodEntryComponent component = new FoodEntryComposite(name, description, calories, protein, carbs, fat);
                for (FoodEntryComponent entry : children) {
                    component.add(entry);
                }

                return component;
            }
            else {
                return new FoodEntry(name, description, calories, protein, carbs, fat);
            }
        }

    }

}
