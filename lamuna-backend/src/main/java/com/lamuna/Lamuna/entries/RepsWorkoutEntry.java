package com.lamuna.Lamuna.entries;

public class RepsWorkoutEntry extends WorkoutEntryDecorator {

    public RepsWorkoutEntry(WorkoutEntry entry) {
        super(entry);
        wrappedEntry.setMinutes(-1); // minutes are irrelevant when completing a repetition based workout
    }

    @Override
    public void setMinutes(int minutes) {
        wrappedEntry.setMinutes(-1); // Repetition based workouts do not take a time
    }
}
