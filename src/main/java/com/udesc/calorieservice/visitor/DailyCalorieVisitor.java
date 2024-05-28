package com.udesc.calorieservice.visitor;

import com.udesc.calorieservice.model.CalorieRecord;
import com.udesc.calorieservice.service.CalorieStrategy;
import com.udesc.calorieservice.service.GainCaloriesStrategy;
import com.udesc.calorieservice.service.LoseCaloriesStrategy;

public class DailyCalorieVisitor implements CalorieVisitor{
    private final int currentCalories;
    private final String goal;
    private int[] result;

    public DailyCalorieVisitor(int currentCalories, String goal) {
        this.currentCalories = currentCalories;
        this.goal = goal;
    }

    @Override
    public void visit(CalorieRecord calorieRecord) {
        CalorieStrategy strategy;
        if ("engordar".equalsIgnoreCase(goal)) {
            strategy = new GainCaloriesStrategy();
        } else if ("emagrecer".equalsIgnoreCase(goal)) {
            strategy = new LoseCaloriesStrategy();
        } else {
            result = new int[]{};
            return;
        }
        result = strategy.calculate(currentCalories);
    }

    public int[] getResult() {
        return result;
    }
}
