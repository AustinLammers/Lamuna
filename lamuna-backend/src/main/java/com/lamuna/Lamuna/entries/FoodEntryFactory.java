package com.lamuna.Lamuna.entries;

import org.springframework.stereotype.Component;

@Component
public class FoodEntryFactory {

    public FoodEntryComponent createFoodItem (String name, String description, int calories, double protein, double carbs, double fat) {
        return new FoodEntry(name, description, calories, protein, carbs, fat);
    }

    public FoodEntryComponent createMealItem (String name, String description, int calories, double protein, double carbs, double fat) {
        return new FoodEntryComposite(name, description, calories, protein, carbs, fat);
    }

    public FoodEntryComponent createMealItem (String name, String description, int calories) {
        return new FoodEntryComposite(name, description, calories);
    }

    public FoodEntryComponent createMealItem (String name, String description) {
        return new FoodEntryComposite(name, description, 0);
    }

}
