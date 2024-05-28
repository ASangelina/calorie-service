package com.udesc.calorieservice.service;

import com.udesc.calorieservice.model.CalorieRecord;
import com.udesc.calorieservice.notifier.CalorieObserver;
import com.udesc.calorieservice.repository.CalorieRepository;
import com.udesc.calorieservice.visitor.DailyCalorieVisitor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalorieServiceImpl implements CalorieService{
    private final CalorieRepository calorieRepository;
    private final List<CalorieObserver> calorieObservers = new ArrayList<>();


    public CalorieServiceImpl(CalorieRepository calorieRepository) {
        this.calorieRepository = calorieRepository;
    }

    public void registerObserver(CalorieObserver observer) {
        calorieObservers.add(observer);
    }

    public void removeObserver(CalorieObserver observer) {
        calorieObservers.remove(observer);
    }

    @Override
    public int[] calculateDailyCalories(int currentCalories, String goal) {
        DailyCalorieVisitor visitor = new DailyCalorieVisitor(currentCalories, goal);
        // Supondo que você tenha um registro que queira visitar
        // Aqui, poderia ser o registro específico baseado em alguma lógica
        CalorieRecord calorieRecord = new CalorieRecord(); // Substitua com o registro real
        calorieRecord.accept(visitor);
        return visitor.getResult();
    }

    @Override
    public CalorieRecord saveCalorie(CalorieRecord calorieRecord) {
        CalorieRecord savedRecord = calorieRepository.save(calorieRecord);
        notifyObservers(savedRecord);
        return savedRecord;
    }

    @Override
    public List<CalorieRecord> getAllCalories() {
        return calorieRepository.findAll();
    }

    @Override
    public List<CalorieRecord> getCaloriesByGoal(String goal) {
        return calorieRepository.findByGoal(goal);
    }

    @Override
    public List<CalorieRecord> getCaloriesByCaloriesRange(int minCalories, int maxCalories) {
        return calorieRepository.findByCaloriesBetween(minCalories, maxCalories);
    }

    private void notifyObservers(CalorieRecord calorieRecord) {
        for (CalorieObserver observer : calorieObservers) {
            observer.update(calorieRecord);
        }
    }
}
