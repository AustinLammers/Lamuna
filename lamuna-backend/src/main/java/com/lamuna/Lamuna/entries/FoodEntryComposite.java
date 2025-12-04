package com.lamuna.Lamuna.entries;

import java.util.ArrayList;
import java.util.List;

public class FoodEntryComposite extends FoodEntryComponent {
    private List<FoodEntryComponent> children = new ArrayList<>();

    public FoodEntryComposite(String name, String description, int calories) {
        super(name, description, calories);

    }

    public FoodEntryComposite(String name, String description, int calories, double protein, double carbs, double fat) {
        super(name, description, calories, protein, carbs, fat);

    }

    @Override
    public int getCalories() {
        int calories = super.getCalories();
        for (FoodEntryComponent child : children) {
            calories += child.getCalories();
        }

        return calories;
    }

    @Override
    public double getProtein() {
        double protein = super.getProtein();
        for (FoodEntryComponent child : children) {
            protein += child.getProtein();
        }

        return protein;
    }

    @Override
    public double getCarbs() {
        double carbs = super.getCarbs();
        for (FoodEntryComponent child : children) {
            carbs += child.getCarbs();
        }

        return carbs;
    }

    @Override
    public double getFat() {
        double fat = super.getFat();
        for (FoodEntryComponent child : children) {
            fat += child.getFat();
        }

        return fat;
    }

    @Override
    public String getDescription() {
        String description = super.getDescription();
        description += " with";
        for (FoodEntryComponent child : children) {
            description += ", ";
            description += child.getDescription();
        }

        return description;
    }

    @Override
    public void add (FoodEntryComponent entry) {
        children.add(entry);
    }

    @Override
    public void remove(FoodEntryComponent entry) {
        children.remove(entry);
    }


}
