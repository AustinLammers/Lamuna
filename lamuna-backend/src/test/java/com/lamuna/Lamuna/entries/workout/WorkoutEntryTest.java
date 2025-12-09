package com.lamuna.Lamuna.entries.workout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutEntryTest {

    @Test
    public void testConstructor() {
        WorkoutEntry workout = new WorkoutEntry("Situps", "Morning Situps", 500);

        assertEquals("Situps", workout.getName());
        assertEquals("Morning Situps", workout.getDescription());
        assertEquals(500, workout.getCalories());



    }

    @Test
    public void testSetters() {
        WorkoutEntry workout = new WorkoutEntry("Situps", "Morning Situps", 500);

        workout.setReps(15);
        workout.setSets(2);
        workout.setMinutes(15);

        assertEquals(15, workout.getReps());
        assertEquals(2, workout.getSets());
        assertEquals(15, workout.getMinutes());
    }
}
