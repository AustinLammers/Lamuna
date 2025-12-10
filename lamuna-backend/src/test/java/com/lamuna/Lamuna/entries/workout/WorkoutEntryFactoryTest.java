package com.lamuna.Lamuna.entries.workout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutEntryFactoryTest {

    @Test
    public void createRepsWorkoutEntry() {
        WorkoutEntryFactory workoutEntryFactory = new WorkoutEntryFactory();
        WorkoutEntry newRepsWorkout = workoutEntryFactory.createWorkoutEntry(WorkoutType.REPS,"Push-Ups", "Evening Pushups", 200, 2, 30, 0);

        assertEquals("Push-Ups", newRepsWorkout.getName());
        assertEquals("Evening Pushups", newRepsWorkout.getDescription());
        assertEquals(200, newRepsWorkout.getCalories());
        assertEquals(2, newRepsWorkout.getSets());

        newRepsWorkout.setMinutes(15);

        assertEquals(0, newRepsWorkout.getMinutes());
    }

    @Test
    public void createTimedWorkoutEntry() {
        WorkoutEntryFactory workoutEntryFactory = new WorkoutEntryFactory();
        WorkoutEntry newTimedWorkout = workoutEntryFactory.createWorkoutEntry(WorkoutType.TIMED,"Jog", "Morning Jog", 500, 0, 0, 60);
        assertEquals("Jog", newTimedWorkout.getName());
        assertEquals("Morning Jog", newTimedWorkout.getDescription());
        assertEquals(500, newTimedWorkout.getCalories());
        assertEquals(60, newTimedWorkout.getMinutes());

        newTimedWorkout.setReps(15);
        newTimedWorkout.setSets(3);

        assertEquals(0, newTimedWorkout.getReps());
        assertEquals(0, newTimedWorkout.getSets());
    }
}
