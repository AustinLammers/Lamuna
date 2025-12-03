package com.lamuna.Lamuna.entries;

public class WorkoutEntryDecorator extends WorkoutEntry {
    protected WorkoutEntry wrappedEntry;

    public WorkoutEntryDecorator(WorkoutEntry entry) {
        super();
        wrappedEntry = entry;
    }

    @Override
    public String getName() {
        return wrappedEntry.getName();
    }

    @Override
    public void setName(String name) {
        wrappedEntry.setName(name);
    }

    @Override
    public int getCalories() {
        return wrappedEntry.getCalories();
    }

    @Override
    public void setCalories(int calories) {
        wrappedEntry.setCalories(calories);
    }

    @Override
    public String getDescription() {
        return wrappedEntry.getDescription();
    }
    @Override
    public void setDescription(String description) {
        wrappedEntry.setDescription(description);
    }

    @Override
    public int getMinutes() {
        return wrappedEntry.getMinutes();
    }

    @Override
    public void setMinutes(int minutes) {
        wrappedEntry.setMinutes(minutes);
    }

    @Override
    public int getReps() {
        return wrappedEntry.getReps();
    }

    @Override
    public void setReps(int reps) {
        wrappedEntry.setReps(reps);
    }

    @Override
    public int getSets() {
        return wrappedEntry.getSets();
    }

    @Override
    public void setSets(int sets) {
        wrappedEntry.setSets(sets);
    }

}
