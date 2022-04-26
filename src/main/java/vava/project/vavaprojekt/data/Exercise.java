package vava.project.vavaprojekt.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Exercise {

    private Integer id;
    private String name;
    private String description;
    private Integer sets;
    private Integer completed_sets;
    private Integer repetitions;
    private Double extra_weight;
    private Date rest;
    private Date time_for_rep;

    public Exercise(Integer id, String name, String description, Integer sets, Integer completed_sets, Integer repetitions,
                    Double extra_weight, String rest, String time_for_rep) throws Exception
    {

        DateFormat f = new SimpleDateFormat("hh:mm:ss");

        this.id = id;
        this.name = name;
        this.description = description;

        this.sets = sets;
        this.completed_sets = completed_sets;
        this.repetitions = repetitions;
        this.extra_weight = extra_weight;

        this.rest = f.parse(rest);
        this.time_for_rep = f.parse(time_for_rep);
    }

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

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getCompleted_sets() {
        return completed_sets;
    }

    public void setCompleted_sets(Integer completed_sets) {
        this.completed_sets = completed_sets;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }

    public Double getExtra_weight() {
        return extra_weight;
    }

    public void setExtra_weight(Double extra_weight) {
        this.extra_weight = extra_weight;
    }

    public Date getRest() {
        return rest;
    }

    public void setRest(Date rest) {
        this.rest = rest;
    }

    public Date getTime_for_rep() {
        return time_for_rep;
    }

    public void setTime_for_rep(Date time_for_rep) {
        this.time_for_rep = time_for_rep;
    }

    public boolean isCompleted() {
        if (this.sets.equals(this.completed_sets)) return true;
        else return false;
    }
}
