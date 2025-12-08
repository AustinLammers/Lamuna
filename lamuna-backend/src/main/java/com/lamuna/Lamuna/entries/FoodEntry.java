package com.lamuna.Lamuna.entries;

import java.time.LocalDate;
import java.util.Iterator;

public class FoodEntry extends FoodEntryComponent {

    public FoodEntry(String name, String description, int calories) {
        super(name, description, calories);


    }

    public FoodEntry(String name, String description, int calories,  double protein, double carbs, double fat) {
        super(name, description, calories, protein, carbs, fat);

    }
}
