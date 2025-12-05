package com.lamuna.Lamuna.services;

import com.lamuna.Lamuna.dto.CreateFoodLogRequest;
import com.lamuna.Lamuna.dto.FoodLogResponse;
import com.lamuna.Lamuna.entries.FoodEntryComponent;
import com.lamuna.Lamuna.entries.FoodEntryFactory;
import com.lamuna.Lamuna.entities.FoodLogEntity;
import com.lamuna.Lamuna.repositories.FoodLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class FoodLogService {
    private final FoodLogRepository foodLogRepository;
    private final FoodEntryFactory foodEntryFactory = new FoodEntryFactory();
    private static final Set<String> MEAL_NAMES = Set.of("Breakfast", "Lunch", "Dinner", "Snack");

    FoodLogService(FoodLogRepository foodLogRepository) {
        this.foodLogRepository = foodLogRepository;
    }

    public List<FoodLogResponse> getAllFoodRows() {
        List<FoodLogEntity> foodRows = foodLogRepository.findAll();
        Map<Long, List<FoodLogEntity>> childrenByParent = new HashMap<>();

        for (FoodLogEntity row : foodRows) {
            Long parentId = row.getParentLogId();
            if (parentId != null) {
                List<FoodLogEntity> kids = childrenByParent.get(parentId);
                if (kids == null) {
                    kids = new ArrayList<>();
                    childrenByParent.put(parentId, kids);
                }
                kids.add(row);
            }
        }

        List<FoodLogResponse> responses = new ArrayList<>();
        for (FoodLogEntity row : foodRows) {
            if (row.getParentLogId() == null) {
                FoodEntryComponent composite = buildComposite(row, childrenByParent);
                responses.add(toAggregatedResponse(row, composite));
            } else {
                responses.add(toBasicResponse(row));
            }
        }

        return responses;
    }

    public FoodLogResponse create(CreateFoodLogRequest requestToCreateRow) {
        return toBasicResponse(setRequestFields(requestToCreateRow));
    }

    public void delete(Long id) {
        foodLogRepository.deleteById(id);
    }

    // Helpers
    private FoodLogResponse toBasicResponse(FoodLogEntity foodRow) {
        FoodLogResponse newResponseRow = new FoodLogResponse();

        newResponseRow.setId(foodRow.getId());
        newResponseRow.setName(foodRow.getName());
        newResponseRow.setDescription(foodRow.getDescription());
        newResponseRow.setDate(foodRow.getDate());
        newResponseRow.setUserId(foodRow.getUserId());
        newResponseRow.setParentLogId(foodRow.getParentLogId());
        newResponseRow.setCalories(foodRow.getCalories());
        newResponseRow.setProtein(foodRow.getProtein());
        newResponseRow.setCarbs(foodRow.getCarbs());
        newResponseRow.setFat(foodRow.getFat());

        return newResponseRow;
    }

    private FoodEntryComponent buildComposite(FoodLogEntity root, Map<Long, List<FoodLogEntity>> childrenByParent) {
        List<FoodLogEntity> kids = childrenByParent.get(root.getId());
        if (kids == null || kids.isEmpty()) {
            return foodEntryFactory.createFoodItem(
                    root.getName(),
                    root.getDescription(),
                    safeInt(root.getCalories()),
                    safeDouble(root.getProtein()),
                    safeDouble(root.getCarbs()),
                    safeDouble(root.getFat()));
        }

        FoodEntryComponent meal = foodEntryFactory.createMealItem(
                root.getName(),
                root.getDescription(),
                safeInt(root.getCalories()),
                safeDouble(root.getProtein()),
                safeDouble(root.getCarbs()),
                safeDouble(root.getFat()));

        for (FoodLogEntity child : kids) {
            meal.add(buildComposite(child, childrenByParent));
        }

        return meal;
    }

    private FoodLogResponse toAggregatedResponse(FoodLogEntity foodRow, FoodEntryComponent composite) {
        FoodLogResponse response = new FoodLogResponse();
        response.setId(foodRow.getId());
        response.setName(foodRow.getName());
        response.setDescription(foodRow.getDescription());
        response.setDate(foodRow.getDate());
        response.setUserId(foodRow.getUserId());
        response.setParentLogId(foodRow.getParentLogId());

        if (MEAL_NAMES.contains(foodRow.getName())) {
            response.setCalories((int) composite.getCalories());
            response.setProtein(composite.getProtein());
            response.setCarbs(composite.getCarbs());
            response.setFat(composite.getFat());
        } else {
            response.setCalories(foodRow.getCalories());
            response.setProtein(foodRow.getProtein());
            response.setCarbs(foodRow.getCarbs());
            response.setFat(foodRow.getFat());
        }

        return response;
    }

    private FoodLogEntity setRequestFields(CreateFoodLogRequest requestToCreateRow) {
        FoodLogEntity newRow = new FoodLogEntity();

        newRow.setName(requestToCreateRow.getName());
        newRow.setDescription(requestToCreateRow.getDescription());
        newRow.setDate(requestToCreateRow.getDate());
        newRow.setCalories(requestToCreateRow.getCalories());
        newRow.setProtein(requestToCreateRow.getProtein());
        newRow.setCarbs(requestToCreateRow.getCarbs());
        newRow.setFat(requestToCreateRow.getFat());
        newRow.setUserId(requestToCreateRow.getUserId());
        newRow.setParentLogId(requestToCreateRow.getParentLogId());

        return foodLogRepository.save(newRow);
    }

    private int safeInt(Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    private double safeDouble(Double value) {
        if (value == null) {
            return 0.0;
        }
        return value;
    }
}
