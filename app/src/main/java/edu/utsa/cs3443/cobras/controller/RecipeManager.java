package edu.utsa.cs3443.cobras.controller;
import android.content.Context;
import edu.utsa.cs3443.cobras.model.Recipe;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Manages the master list of all recipes, including loading and filtering.
 */
public class RecipeManager {
    private static RecipeManager instance;
    private final List<Recipe> allRecipes;

    private RecipeManager(Context context) {
        DataLoader dataLoader = new DataLoader();
        this.allRecipes = dataLoader.loadRecipesFromCsv(context);
        // debug print statement
        Log.d("RecipeManager", "Loaded " + this.allRecipes.size() + " recipes from CSV.");
    }

    /**
     * Gets singleton instance of RecipeManager.
     * @param context The application context.
     * @return The singleton instance.
     */
    public static synchronized RecipeManager getInstance(Context context) {
        if (instance == null) {
            instance = new RecipeManager(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * Filters recipes based on protein type and maximum price.
     * @param protein The protein type to filter by
     * @param maxPrice The maximum price category
     * @return A new list of matching recipes.
     */
    public List<Recipe> getRecipesByFilter(String protein, int maxPrice) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (recipe.getProteinType().equalsIgnoreCase(protein) && recipe.getPriceRangeMax() <= maxPrice) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    /**
     * Retrieves a single recipe by its unique ID.
     * @param recipeId The ID of the recipe to find.
     * @return The Recipe object, or null if not found.
     */
    public Recipe getRecipeById(String recipeId) {
        for (Recipe recipe : allRecipes) {
            if (recipe.getRecipeId().equals(recipeId)) {
                return recipe;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of all favorited recipes.
     * @param favoriteIds A set of favorited recipe IDs.
     * @return A new list of the favorited Recipe objects.
     */
    public List<Recipe> getFavoritedRecipes(Set<String> favoriteIds) {
        List<Recipe> favoritedRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (favoriteIds.contains(recipe.getRecipeId())) {
                favoritedRecipes.add(recipe);
            }
        }
        return favoritedRecipes;
    }
}
