package com.lamuna.Lamuna.entries;

public class WorkoutEntryFactory {
    public WorkoutEntry createRepWorkoutEntry(String name, String description, int calories, int sets, int repsPerSet) {
        WorkoutEntry entry = new RepsWorkoutEntry(new WorkoutEntry(name, description, calories));
        entry.setSets(sets);
        entry.setReps(repsPerSet);

        return entry;
    }

    public WorkoutEntry createTimedWorkoutEntry(String name, String description, int calories, int minutes) {
        WorkoutEntry entry = new TimedWorkoutEntry(new WorkoutEntry(name, description, calories));
        entry.setMinutes(minutes);

        return entry;
    }
}
