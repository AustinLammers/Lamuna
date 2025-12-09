package com.lamuna.Lamuna.entries.food;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FoodEntryComposite extends FoodEntryComponent {
    private List<FoodEntryComponent> children = new ArrayList<>();

    public FoodEntryComposite(String name, String description, int calories) {
        super(name, description, calories);

    }

    public FoodEntryComposite(String name, String description, int calories, double protein, double carbs, double fat) {
        super(name, description, calories, protein, carbs, fat);

    }

    @Override
    public int getCalories() {
        int calories = super.getCalories();
        for (FoodEntryComponent child : children) {
            calories += child.getCalories();
        }

        return calories;
    }

    @Override
    public double getProtein() {
        double protein = super.getProtein();
        for (FoodEntryComponent child : children) {
            protein += child.getProtein();
        }

        return protein;
    }

    @Override
    public double getCarbs() {
        double carbs = super.getCarbs();
        for (FoodEntryComponent child : children) {
            carbs += child.getCarbs();
        }

        return carbs;
    }

    @Override
    public double getFat() {
        double fat = super.getFat();
        for (FoodEntryComponent child : children) {
            fat += child.getFat();
        }

        return fat;
    }

    @Override
    public String getDescription() {
        String description = super.getDescription();
        description += " with";
        for (FoodEntryComponent child : children) {
            description += ", ";
            description += child.getDescription();
        }

        return description;
    }

    @Override
    protected void add (FoodEntryComponent entry) {
        children.add(entry);
    }

    @Override
    protected void remove(FoodEntryComponent entry) {
        children.remove(entry);
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public Iterator<FoodEntryComponent> iterator() {
        return new FoodCompositeIterator();
    }

    private class FoodCompositeIterator extends FoodIterator {

        int cursor = 0;
        Iterator<FoodEntryComponent> currentIterator;
        FoodCompositeIterator() {};
        @Override
        public boolean hasNext() {
            if (currentIterator == null || !currentIterator.hasNext()) {
                if (cursor == children.size()) {
                    return false;
                }
            }

            return true;
        }

        @Override
        public FoodEntryComponent next() {

            if(currentIterator == null || !currentIterator.hasNext()) {
                if (cursor < children.size()) {
                    Iterator<FoodEntryComponent> foodEntryIterator = children.get(cursor++).iterator();
                    if (foodEntryIterator.hasNext()) {
                        currentIterator = foodEntryIterator;
                        return currentIterator.next();
                    } else {
                        return null;
                    }
                }
            }

            return currentIterator.next();
        }
    }
}
