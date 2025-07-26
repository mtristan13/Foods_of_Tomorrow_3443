package edu.utsa.cs3443.cobras.model;

//import com.example.mealapp.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class FavoritesManager {
    private static List<Recipe> favoriteRecipes = new ArrayList<>();

    public static void addFavorite(Recipe recipe) {
        if (!favoriteRecipes.contains(recipe)) {
            favoriteRecipes.add(recipe);
        }
    }

    public static void removeFavorite(Recipe recipe) {
        favoriteRecipes.remove(recipe);
    }

    public static List<Recipe> getFavorites() {
        return new ArrayList<>(favoriteRecipes); // return a copy
    }
}
