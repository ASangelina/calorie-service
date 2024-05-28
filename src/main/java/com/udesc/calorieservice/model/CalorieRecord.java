package com.udesc.calorieservice.model;

import com.udesc.calorieservice.visitor.CalorieVisitor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("CALORIERECORD")
public class CalorieRecord {
    @Id
    private Long id;
    private int calories;
    private String goal;

    public CalorieRecord() {
    }

    public CalorieRecord(int calories, String goal) {
        this.calories = calories;
        this.goal = goal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void accept(CalorieVisitor visitor) {
        visitor.visit(this);
    }
}
