package com.lamuna.Lamuna.entries.food;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodEntryComponentBuilderTest {

    @Test
    public void testCannotBuildCompositeWithNoChildren() {

        assertThrows(IllegalStateException.class, () -> FoodEntryComponent.getBuilder()
                .createAsMealItem()
                .name("Watermelon")
                .description("1 pound watermelon")
                .calories(100)
                .protein(0.0)
                .carbs(200.0)
                .fat(13.0)
                .build());
    }
}
