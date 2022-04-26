package vava.project.vavaprojekt.data;

import java.util.ArrayList;

public final class Exercise {

    private Integer id;
    private String name;
    private String description;
    private Integer sets;
    private Integer completed_sets;
    private Integer repetitions;
    private Double extra_weight;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        if (this.sets.equals(this.completed_sets)) return true;
        else return false;
    }
}
