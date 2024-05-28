package com.udesc.calorieservice.controller;

import com.udesc.calorieservice.model.CalorieRecord;
import com.udesc.calorieservice.notifier.CalorieObserver;
import com.udesc.calorieservice.service.CalorieService;
import com.udesc.calorieservice.service.CalorieServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calories")
public class CalorieController implements CalorieObserver {
    private final CalorieServiceImpl calorieService;

    public CalorieController(CalorieServiceImpl calorieService) {

        this.calorieService = calorieService;
        this.calorieService.registerObserver(this);
    }

    @PostMapping("/calculate")
    public int[] calculateCalories(@RequestBody CalorieRecord calorieRecord) {
        return calorieService.calculateDailyCalories(calorieRecord.getCalories(), calorieRecord.getGoal());
    }

    @PostMapping("/save")
    public CalorieRecord saveCalorie(@RequestBody CalorieRecord calorieRecord) {
        return calorieService.saveCalorie(calorieRecord);
    }

    @GetMapping("/all")
    public List<CalorieRecord> getAllCalories() {
        return calorieService.getAllCalories();
    }

    @GetMapping("/goal/{goal}")
    public List<CalorieRecord> getCaloriesByGoal(@PathVariable String goal) {
        return calorieService.getCaloriesByGoal(goal);
    }

    @GetMapping("/range")
    public List<CalorieRecord> getCaloriesByCaloriesRange(@RequestParam int min, @RequestParam int max) {
        return calorieService.getCaloriesByCaloriesRange(min, max);
    }

    @Override
    public void update(CalorieRecord calorieRecord) {
        System.out.println("Atualização recebida para o registro de calorias com ID: " + calorieRecord.getId());


    }
}
