package com.lamuna.Lamuna.entries;

import java.time.LocalDate;

public abstract class WorkoutEntryComponent extends Entry{

    private int minutes;
    private int reps;
    private int sets;

    public WorkoutEntryComponent(String name, String description, int calories) {
        super(name, description, calories);
    }

    public WorkoutEntryComponent() {
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

    public void add(WorkoutEntry workoutEntry){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(WorkoutEntryComponent workoutEntryComponent){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
