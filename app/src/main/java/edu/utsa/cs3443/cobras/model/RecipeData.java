package edu.utsa.cs3443.cobras.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeData {

    public static List<Recipe> getRecipesByCategory(String category) {
        List<Recipe> recipes = new ArrayList<>();

        switch (category) {
            case "chicken":
                recipes.add(new Recipe("Grilled Chicken", "chicken",
                        Arrays.asList("Chicken breast", "Salt", "Pepper"),
                        Arrays.asList("Season chicken", "Grill for 10 minutes", "Serve hot")));

                recipes.add(new Recipe("Chicken Alfredo", "chicken",
                        Arrays.asList("Chicken", "Pasta", "Alfredo sauce"),
                        Arrays.asList("Boil pasta", "Cook chicken", "Mix with sauce")));

                recipes.add(new Recipe("Chicken Soup", "chicken",
                        Arrays.asList("Chicken", "Carrots", "Onion", "Broth"),
                        Arrays.asList("Boil chicken", "Add veggies", "Simmer for 30 minutes")));
                break;

            case "beef":
                recipes.add(new Recipe("Beef Tacos", "beef",
                        Arrays.asList("Beef", "Taco shells", "Cheese"),
                        Arrays.asList("Cook beef", "Fill shells", "Add cheese")));

                recipes.add(new Recipe("Beef Stroganoff", "beef",
                        Arrays.asList("Beef", "Mushrooms", "Cream"),
                        Arrays.asList("Cook beef", "Add mushrooms", "Stir in cream")));

                recipes.add(new Recipe("Beef Stew", "beef",
                        Arrays.asList("Beef chunks", "Potatoes", "Carrots"),
                        Arrays.asList("Brown beef", "Add veggies", "Simmer for 1 hour")));
                break;

            case "pork":
                recipes.add(new Recipe("Pulled Pork", "pork",
                        Arrays.asList("Pork shoulder", "BBQ sauce", "Buns"),
                        Arrays.asList("Cook pork slowly", "Shred pork", "Mix with sauce")));

                recipes.add(new Recipe("Pork Chops", "pork",
                        Arrays.asList("Pork chops", "Salt", "Garlic"),
                        Arrays.asList("Season pork", "Fry in pan", "Let rest")));

                recipes.add(new Recipe("Pork Fried Rice", "pork",
                        Arrays.asList("Rice", "Pork", "Egg", "Vegetables"),
                        Arrays.asList("Cook pork", "Add rice and veggies", "Fry with egg")));
                break;
        }

        return recipes;
    }
}
