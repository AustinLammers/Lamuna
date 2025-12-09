package com.lamuna.Lamuna.controllers;

import com.lamuna.Lamuna.dto.CreateFoodLogRequest;
import com.lamuna.Lamuna.dto.FoodLogResponse;
import com.lamuna.Lamuna.services.FoodLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/foods")
@RestController
public class FoodLogController {
    FoodLogService foodLogService;

    public FoodLogController(FoodLogService foodLogService) {
        this.foodLogService = foodLogService;
    }

    @GetMapping
    public List<FoodLogResponse> getAllFoodRows(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return foodLogService.getAllFoodRows(date);
    }

    @PostMapping
    public FoodLogResponse createFoodRow(@RequestBody CreateFoodLogRequest foodRequest) {
        return foodLogService.create(foodRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodRow(@PathVariable Long id) {
        foodLogService.delete(id);
    }
}
