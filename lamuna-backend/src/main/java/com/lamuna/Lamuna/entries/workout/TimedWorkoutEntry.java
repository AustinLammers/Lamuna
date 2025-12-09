package com.lamuna.Lamuna.entries.workout;

public class TimedWorkoutEntry extends WorkoutEntryDecorator {

    public TimedWorkoutEntry(WorkoutEntry entry) {
        super(entry);
        wrappedEntry.setReps(-1);
        wrappedEntry.setSets(-1);
    }

    @Override
    public void setSets(int sets) {
        wrappedEntry.setSets(-1); // timed workouts do not have sets
    }

    @Override
    public void setReps(int reps) {
        wrappedEntry.setReps(-1); // timed workouts do not have reps
    }

}
