package com.lamuna.Lamuna.entries.workout;

public class TimedWorkoutEntry extends WorkoutEntryDecorator {

    public TimedWorkoutEntry(WorkoutEntry entry) {
        super(entry);
        wrappedEntry.setReps(0);
        wrappedEntry.setSets(0);
    }

    @Override
    public void setSets(int sets) {
        wrappedEntry.setSets(0); // timed workouts do not have sets
    }

    @Override
    public void setReps(int reps) {
        wrappedEntry.setReps(0); // timed workouts do not have reps
    }

}
