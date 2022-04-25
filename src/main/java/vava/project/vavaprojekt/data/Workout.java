package vava.project.vavaprojekt.data;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Workout {
    private String name;
    private Integer owner;
    private String description;
    private LocalDateTime scheduled_for;
    private ArrayList<Exercise> exercises;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getScheduled_for() {
        return scheduled_for;
    }

    public void setScheduled_for(LocalDateTime scheduled_for) {
        this.scheduled_for = scheduled_for;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Exercise getOneExercise(int id) {
        return this.exercises.get(id);
    }

    public Exercise setOneExercise(int id, Exercise e) {
        return this.exercises.set(id,e);
    }

    public double getProgress()
    {
        int all = this.exercises.size();
        double done = 0;

        for (Exercise e : this.exercises) if (e.isCompleted()) ++done;

        return done / all;
    }

}
