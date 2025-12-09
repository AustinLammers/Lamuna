package com.lamuna.Lamuna.entries;

import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

@Component
public class WorkoutEntryFactory {
    private WorkoutEntry createRepWorkoutEntry(String name, String description, int calories, int sets, int repsPerSet) {
        WorkoutEntry entry = new RepsWorkoutEntry(new WorkoutEntry(name, description, calories));
        entry.setSets(sets);
        entry.setReps(repsPerSet);

        return entry;
    }

    private WorkoutEntry createTimedWorkoutEntry(String name, String description, int calories, int minutes) {
        WorkoutEntry entry = new TimedWorkoutEntry(new WorkoutEntry(name, description, calories));
        entry.setMinutes(minutes);

        return entry;
    }

    public WorkoutEntry createWorkoutEntry(WorkoutType type ,String name, String description, int calories, int sets, int repsPerSet, int minutes) {

        if(type == WorkoutType.REPS) {
            return createRepWorkoutEntry(name, description, calories, sets, repsPerSet);
        }
        else {
            return createTimedWorkoutEntry(name, description, calories, minutes);
        }
    }

}
