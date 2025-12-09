package com.lamuna.Lamuna.entries.food;

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

        FoodEntryComponent cherries = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Cherries")
                .description("2 cherries")
                .calories(35)
                .protein(0.0)
                .carbs(25.0)
                .fat(30.0)
                .build();


        FoodEntryComponent milk = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("milk")
                .description("1 cup of milk")
                .calories(200)
                .protein(10.0)
                .carbs(25.0)
                .fat(50.0)
                .build();

        FoodEntryComponent proteinPowder = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Protein Powder")
                .description("1 scoop protein powder")
                .calories(35)
                .protein(135.0)
                .carbs(25.0)
                .fat(30.0)
                .build();

        FoodEntryComponent shake = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Shake")
                .description("1 pound shake")
                .calories(0)
                .addIngredient(cherries)
                .addIngredient(milk)
                .addIngredient(proteinPowder)
                .build();


        assertEquals(cherries.getCalories() + milk.getCalories() + proteinPowder.getCalories(), shake.getCalories());
        assertEquals(cherries.getProtein() + milk.getProtein() + proteinPowder.getProtein(), shake.getProtein());
        assertEquals(cherries.getCarbs() + milk.getCarbs() + proteinPowder.getCarbs(), shake.getCarbs());
        assertEquals(cherries.getFat() + milk.getFat() + proteinPowder.getFat(), shake.getFat());
    }

    @Test
    public void testCompositeWithBaseValues() {

        FoodEntryComponent cherries = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Cherries")
                .description("2 cherries")
                .calories(35)
                .protein(0.0)
                .carbs(25.0)
                .fat(30.0)
                .build();


        FoodEntryComponent milk = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("milk")
                .description("1 cup of milk")
                .calories(200)
                .protein(10.0)
                .carbs(25.0)
                .fat(50.0)
                .build();

        FoodEntryComponent proteinPowder = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Protein Powder")
                .description("1 scoop protein powder")
                .calories(35)
                .protein(135.0)
                .carbs(25.0)
                .fat(30.0)
                .build();

        FoodEntryComponent shake = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Shake")
                .description("1 pound shake")
                .calories(30)
                .protein(5.0)
                .carbs(10.0)
                .fat(20.0)
                .addIngredient(cherries)
                .addIngredient(milk)
                .addIngredient(proteinPowder)
                .build();

        assertEquals(cherries.getCalories() + milk.getCalories() + proteinPowder.getCalories() + 30, shake.getCalories());
        assertEquals(cherries.getProtein() + milk.getProtein() + proteinPowder.getProtein() + 5, shake.getProtein());
        assertEquals(cherries.getCarbs() + milk.getCarbs() + proteinPowder.getCarbs() + 10, shake.getCarbs());
        assertEquals(cherries.getFat() + milk.getFat() + proteinPowder.getFat() + 20, shake.getFat());
    }

    @Test
    public void testCompositeDescription() {
        FoodEntryComponent cherries = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Cherries")
                .description("2 cherries")
                .calories(35)
                .protein(0.0)
                .carbs(25.0)
                .fat(30.0)
                .build();


        FoodEntryComponent milk = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("milk")
                .description("1 cup of milk")
                .calories(200)
                .protein(10.0)
                .carbs(25.0)
                .fat(50.0)
                .build();

        FoodEntryComponent proteinPowder = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Protein Powder")
                .description("1 scoop protein powder")
                .calories(35)
                .protein(135.0)
                .carbs(25.0)
                .fat(30.0)
                .build();

        FoodEntryComponent shake = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Shake")
                .description("1 pound shake")
                .calories(30)
                .protein(5.0)
                .carbs(10.0)
                .fat(20.0)
                .addIngredient(cherries)
                .addIngredient(milk)
                .addIngredient(proteinPowder)
                .build();


        FoodEntryComponent lettuce = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Lettuce")
                .description("1 pound lettuce")
                .calories(70)
                .protein(0.0)
                .carbs(0.0)
                .fat(0.0)
                .build();

        FoodEntryComponent tomato = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Tomato")
                .description("Sliced Tomato")
                .calories(120)
                .protein(0.0)
                .carbs(30.0)
                .fat(5.0)
                .build();


        FoodEntryComponent onion = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("onion")
                .description("diced onion")
                .calories(50)
                .protein(0.0)
                .carbs(20.0)
                .fat(5.0)
                .build();

        FoodEntryComponent salad = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("salad")
                .description("diced salad")
                .addIngredient(lettuce)
                .addIngredient(tomato)
                .addIngredient(onion)
                .build();


        FoodEntryComponent lunch = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("lunch")
                .description("Lunch")
                .addIngredient(salad)
                .addIngredient(shake)
                .build();

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
    public void testGetIngredients() {

        // salad items
        FoodEntryComponent lettuce = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Lettuce")
                .description("1 pound lettuce")
                .calories(70)
                .protein(0.0)
                .carbs(0.0)
                .fat(0.0)
                .build();

        FoodEntryComponent tomato = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Tomato")
                .description("Sliced Tomato")
                .calories(120)
                .protein(0.0)
                .carbs(30.0)
                .fat(5.0)
                .build();

        FoodEntryComponent onion = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("onion")
                .description("diced onion")
                .calories(50)
                .protein(0.0)
                .carbs(20.0)
                .fat(5.0)
                .build();

        FoodEntryComponent salad = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("salad")
                .description("diced salad")
                .addIngredient(lettuce)
                .addIngredient(tomato)
                .addIngredient(onion)
                .build();

        // burger items
        FoodEntryComponent beef = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Ground Beef")
                .description("1/2 pound ground beef")
                .calories(320)
                .protein(20.0)
                .carbs(4.0)
                .fat(10.0)
                .build();

        FoodEntryComponent tomato2 = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Tomato")
                .description("Sliced Tomato")
                .calories(120)
                .protein(0.0)
                .carbs(30.0)
                .fat(5.0)
                .build();

        FoodEntryComponent ketchup = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Ketchup")
                .description("Ketchup")
                .calories(20)
                .protein(0.)
                .carbs(0.)
                .fat(3.)
                .build();

        FoodEntryComponent burger =  FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Burger")
                .description("1/2 pound burger")
                .addIngredient(tomato2)
                .addIngredient(ketchup)
                .addIngredient(beef)
                .build();

        // ice cream items
        FoodEntryComponent milk = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Milk")
                .description("1 cup of milk")
                .calories(200)
                .protein(10.0)
                .carbs(25.0)
                .fat(50.0)
                .build();

        FoodEntryComponent sugar = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Sugar")
                .description("1 cup of sugar")
                .calories(100)
                .protein(0.0)
                .carbs(70.0)
                .fat(0.0)
                .build();

        FoodEntryComponent chocolateSyrup = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Chocolate Syrup")
                .description("Hershey's Syrup")
                .calories(120)
                .protein(0.0)
                .carbs(30.0)
                .fat(0.0)
                .build();

        FoodEntryComponent chocolateIceCream = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Chocolate Ice Cream")
                .description("Homemade Chocolate Ice Cream")
                .addIngredient(sugar)
                .addIngredient(chocolateSyrup)
                .addIngredient(milk)
                .build();


        // shake items
        FoodEntryComponent cherries = FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Cherries")
                .description("2 cherries")
                .calories(35)
                .protein(0.0)
                .carbs(25.0)
                .fat(30.0)
                .build();

        FoodEntryComponent proteinPowder =FoodEntryComponent.getBuilder()
                .createAsIngredientItem()
                .name("Protein Powder")
                .description("1 scoop protein powder")
                .calories(35)
                .protein(135.0)
                .carbs(25.0)
                .fat(30.0)
                .build();

        FoodEntryComponent shake = FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Shake")
                .description("1 pound shake")
                .addIngredient(cherries)
                .addIngredient(proteinPowder)
                .addIngredient(chocolateIceCream)
                .build();

        FoodEntryComponent lunch =  FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Lunch")
                .description("Lunch at Grandma's")
                .addIngredient(salad)
                .addIngredient(burger)
                .addIngredient(shake)
                .build();


        int iterations = 0;

        for (FoodEntryComponent food : lunch) {
            System.out.println(food.getDescription());
            iterations++;
        }

        assertEquals(11, iterations);

    }
}
