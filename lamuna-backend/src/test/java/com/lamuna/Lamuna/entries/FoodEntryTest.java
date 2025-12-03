package com.lamuna.Lamuna.entries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodEntryTest {

    @Test
    public void testConstructor() {
        FoodEntry food = new FoodEntry("Watermelon", "3 pound watermelon", 500);

        assertEquals("Watermelon", food.getName());
        assertEquals("3 pound watermelon", food.getDescription());
        assertEquals(500, food.getCalories());



    }

    @Test
    public void testSetters() {
        FoodEntry food = new FoodEntry("Watermelon", "3 pound watermelon", 500);

        food.setCarbs(200);
        food.setFat(100);
        food.setProtein(10);

        assertEquals(200, food.getCarbs());
        assertEquals(100, food.getFat());
        assertEquals(10, food.getProtein());
    }
}
