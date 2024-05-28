package com.udesc.calorieservice.service;

public class GainCaloriesStrategy implements CalorieStrategy{
    @Override
    public int[] calculate(int currentCalories) {
        int minCalories = currentCalories + 300;
        int maxCalories = currentCalories + 500;
        return new int[]{minCalories, maxCalories};
    }
}
