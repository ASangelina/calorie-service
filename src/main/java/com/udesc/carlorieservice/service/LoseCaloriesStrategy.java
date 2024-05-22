package com.udesc.carlorieservice.service;

public class LoseCaloriesStrategy implements CalorieStrategy{
    @Override
    public int[] calculate(int currentCalories) {
        int minCalories = currentCalories - 500;
        int maxCalories = currentCalories - 1000;
        return new int[]{minCalories, maxCalories};
    }
}
