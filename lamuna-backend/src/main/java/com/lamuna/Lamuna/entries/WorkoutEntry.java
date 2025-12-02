package com.lamuna.Lamuna.entries;

public class WorkoutEntry extends Entry {
    private int minutes;
    private int reps;
    private int sets;

    public WorkoutEntry(String name, String description, int calories) {
        super(name, description, calories);
    }

    public WorkoutEntry() {
        super();
    }

    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getReps() {
        return reps;
    }
    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }
}
