package com.lamuna.Lamuna.entries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodEntryTest {

    @Test
    public void testConstructor() {
        FoodEntry food = new FoodEntry("Watermelon", "3 pound watermelon", 500);

        assertEquals("Watermelon", food.getName());
        assertEquals("3 pound watermelon", food.getDescription());
        assertEquals(500, food.getCalories());



    }

    @Test
    public void testSetters() {
        FoodEntry food = new FoodEntry("Watermelon", "3 pound watermelon", 500);

        food.setCarbs(200);
        food.setFat(100);
        food.setProtein(10);

        assertEquals(200, food.getCarbs());
        assertEquals(100, food.getFat());
        assertEquals(10, food.getProtein());
    }

    @Test
    public void testComposite() {
        FoodEntryFactory factory = new FoodEntryFactory();
        FoodEntryComponent cherries = factory.createFoodItem("Cherries", "2 cherries", 35, 0, 25, 30);
        FoodEntryComponent milk = factory.createFoodItem("milk", "1 cup of milk", 200, 10, 25, 50);
        FoodEntryComponent proteinPowder = factory.createFoodItem("Protein Powder", "1 scoop protein powder", 35, 135, 25, 30);

        FoodEntryComponent shake = factory.createMealItem("Shake", "1 pound shake", 0);

        shake.add(cherries);
        shake.add(milk);
        shake.add(proteinPowder);

        assertEquals(cherries.getCalories() + milk.getCalories() + proteinPowder.getCalories(), shake.getCalories());
        assertEquals(cherries.getProtein() + milk.getProtein() + proteinPowder.getProtein(), shake.getProtein());
        assertEquals(cherries.getCarbs() + milk.getCarbs() + proteinPowder.getCarbs(), shake.getCarbs());
        assertEquals(cherries.getFat() + milk.getFat() + proteinPowder.getFat(), shake.getFat());
    }

    @Test
    public void testCompositeWithBaseValues() {
        FoodEntryFactory factory = new FoodEntryFactory();
        FoodEntryComponent cherries = factory.createFoodItem("Cherries", "2 cherries", 35, 0, 25, 30);
        FoodEntryComponent milk = factory.createFoodItem("milk", "1 cup of milk", 200, 10, 25, 50);
        FoodEntryComponent proteinPowder = factory.createFoodItem("Protein Powder", "1 scoop protein powder", 35, 135, 25, 30);

        FoodEntryComponent shake = factory.createMealItem("Shake", "1 pound shake", 30, 5, 10, 20);

        shake.add(cherries);
        shake.add(milk);
        shake.add(proteinPowder);

        assertEquals(cherries.getCalories() + milk.getCalories() + proteinPowder.getCalories() + 30, shake.getCalories());
        assertEquals(cherries.getProtein() + milk.getProtein() + proteinPowder.getProtein() + 5, shake.getProtein());
        assertEquals(cherries.getCarbs() + milk.getCarbs() + proteinPowder.getCarbs() + 10, shake.getCarbs());
        assertEquals(cherries.getFat() + milk.getFat() + proteinPowder.getFat() + 20, shake.getFat());
    }

    @Test
    public void testCompositeDescription() {
        FoodEntryFactory factory = new FoodEntryFactory();
        FoodEntryComponent cherries = factory.createFoodItem("Cherries", "2 cherries", 35, 0, 25, 30);
        FoodEntryComponent milk = factory.createFoodItem("milk", "1 cup of milk", 200, 10, 25, 50);
        FoodEntryComponent proteinPowder = factory.createFoodItem("Protein Powder", "1 scoop protein powder", 35, 135, 25, 30);

        FoodEntryComponent shake = factory.createMealItem("Shake", "1 pound shake", 30, 5, 10, 20);

        FoodEntryComponent lettuce = factory.createFoodItem("Lettuce", "1 pound lettuce", 70, 0, 0, 0);
        FoodEntryComponent tomato = factory.createFoodItem("Tomato", "Sliced Tomato", 120, 0, 30, 5);
        FoodEntryComponent onion = factory.createFoodItem("onion", "diced onion", 50, 0, 20, 5);

        FoodEntryComponent salad =  factory.createMealItem("salad", "diced salad");

        FoodEntryComponent lunch = factory.createMealItem("lunch", "Lunch");


        shake.add(cherries);
        shake.add(milk);
        shake.add(proteinPowder);

        salad.add(lettuce);
        salad.add(tomato);
        salad.add(onion);

        lunch.add(salad);
        lunch.add(shake);

        String lunchDescription = lunch.getDescription();

        System.out.println(lunchDescription);

       assertTrue(
               lunchDescription.contains("1 pound shake")
               && lunchDescription.contains("2 cherries")
               && lunchDescription.contains("1 cup of milk")
               && lunchDescription.contains("1 scoop protein powder")
               && lunchDescription.contains("1 pound lettuce")
               && lunchDescription.contains("diced onion")
               && lunchDescription.contains("Sliced Tomato")
               && lunchDescription.contains("diced salad")
               && lunchDescription.contains("Lunch")

       );
    }

    @Test
    public void testCompositeRemove() {
        FoodEntryFactory factory = new FoodEntryFactory();

        FoodEntryComponent cherries = factory.createFoodItem("Cherries", "2 cherries", 35, 0, 25, 30);
        FoodEntryComponent milk = factory.createFoodItem("milk", "1 cup of milk", 200, 10, 25, 50);
        FoodEntryComponent proteinPowder = factory.createFoodItem("Protein Powder", "1 scoop protein powder", 35, 135, 25, 30);

        FoodEntryComponent shake = factory.createMealItem("Shake", "1 pound shake", 30, 5, 10, 20);

        shake.add(cherries);
        shake.add(milk);
        shake.add(proteinPowder);

        shake.remove(cherries);

        String shakeDescription = shake.getDescription();

        assertFalse(shakeDescription.contains("2 cherries"));
    }

    @Test
    public void testGetIngredients() {
        FoodEntryFactory factory = new FoodEntryFactory();

        // salad items
        FoodEntryComponent lettuce = factory.createFoodItem("Lettuce", "1 pound lettuce", 70, 0, 0, 0);
        FoodEntryComponent tomato = factory.createFoodItem("Tomato", "Sliced Tomato", 120, 0, 30, 5);
        FoodEntryComponent onion = factory.createFoodItem("onion", "diced onion", 50, 0, 20, 5);

        FoodEntryComponent salad =  factory.createMealItem("salad", "diced salad");
        salad.add(lettuce);
        salad.add(tomato);
        salad.add(onion);

        // burger items
        FoodEntryComponent beef = factory.createFoodItem("Ground Beef", "1/2 pound ground beef", 320, 20, 4, 10);
        FoodEntryComponent tomato2 = factory.createFoodItem("Tomato", "Sliced Tomato", 120, 0, 30, 5);
        FoodEntryComponent ketchup = factory.createFoodItem("Ketchup", "Ketchup", 20, 0, 0, 3);

        FoodEntryComponent burger =  factory.createMealItem("salad", "diced salad");
        burger.add(tomato2);
        burger.add(ketchup);
        burger.add(beef);

        // ice cream items
        FoodEntryComponent milk = factory.createFoodItem("Milk", "1 cup of milk", 200, 10, 25, 50);
        FoodEntryComponent sugar = factory.createFoodItem("Sugar", "1 cup of sugar", 100, 0, 70, 0);
        FoodEntryComponent chocolateSyrup = factory.createFoodItem("Chocolate Syrup", "Hershey's Syrup", 120, 0, 30, 0);

        FoodEntryComponent chocolateIceCream = factory.createMealItem("Chocolate Ice Cream", "Homemade Chocolate Ice Cream");
        chocolateIceCream.add(sugar);
        chocolateIceCream.add(chocolateSyrup);
        chocolateIceCream.add(milk);

        // shake items
        FoodEntryComponent cherries = factory.createFoodItem("Cherries", "2 cherries", 35, 0, 25, 30);
        FoodEntryComponent proteinPowder = factory.createFoodItem("Protein Powder", "1 scoop protein powder", 35, 135, 25, 30);

        FoodEntryComponent shake = factory.createMealItem("Shake", "1 pound shake");
        shake.add(cherries);
        shake.add(proteinPowder);
        shake.add(chocolateIceCream);

        FoodEntryComponent lunch = factory.createMealItem("Lunch", "Lunch at Grandma's");

        lunch.add(salad);
        lunch.add(burger);
        lunch.add(shake);

        int iterations = 0;

        for (FoodEntryComponent food : lunch) {
            System.out.println(food.getDescription());
            iterations++;
        }

        assertEquals(11, iterations);

    }
}
