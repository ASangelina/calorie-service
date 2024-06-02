package com.udesc.calorieservice.controller;

import com.udesc.calorieservice.model.CalorieRecord;
import com.udesc.calorieservice.notifier.CalorieObserver;
import com.udesc.calorieservice.service.CalorieService;
import com.udesc.calorieservice.service.CalorieServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/calories")
public class CalorieController implements CalorieObserver {
    private final CalorieServiceImpl calorieService;
    private final RestTemplate restTemplate;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    public CalorieController(CalorieServiceImpl calorieService, RestTemplate restTemplate) {
        this.calorieService = calorieService;
        this.restTemplate = restTemplate;
        this.calorieService.registerObserver(this);
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateCalories(@RequestBody CalorieRecord calorieRecord, @RequestHeader("Authorization") String jwt) {
        if (validateToken(jwt)) {
            int[] result = calorieService.calculateDailyCalories(calorieRecord.getCalories(), calorieRecord.getGoal());
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido");
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> saveCalorie(@RequestBody CalorieRecord calorieRecord, @RequestHeader("Authorization") String jwt) {
        if (validateToken(jwt)) {
            return ResponseEntity.ok(calorieService.saveCalorie(calorieRecord));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCalories(@RequestHeader("Authorization") String jwt) {
        if (validateToken(jwt)) {
            return ResponseEntity.ok(calorieService.getAllCalories());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido");
        }
    }

    @GetMapping("/goal/{goal}")
    public ResponseEntity<?> getCaloriesByGoal(@PathVariable String goal, @RequestHeader("Authorization") String jwt) {
        if (validateToken(jwt)) {
            return ResponseEntity.ok(calorieService.getCaloriesByGoal(goal));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido");
        }
    }

    @GetMapping("/range")
    public ResponseEntity<?> getCaloriesByCaloriesRange(@RequestParam int min, @RequestParam int max, @RequestHeader("Authorization") String jwt) {
        if (validateToken(jwt)) {
            return ResponseEntity.ok(calorieService.getCaloriesByCaloriesRange(min, max));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token JWT inválido");
        }
    }

    private boolean validateToken(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Boolean> response = restTemplate.exchange(authServiceUrl + "/auth/validate", HttpMethod.GET, entity, Boolean.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return false;
            }
            throw e;
        }
    }

    @Override
    public void update(CalorieRecord calorieRecord) {
        System.out.println("Atualização recebida para o registro de calorias com ID: " + calorieRecord.getId());
    }
}
