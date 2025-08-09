package edu.utsa.cs3443.cobras.model;

import java.util.List;

/**
 * The central data object representing a single recipe with all its details.
 * This is a pure data class.
 */
public class Recipe {
    private final String recipeId;
    private final String name;
    private final String proteinType;
    private final int priceRangeMax;
    private final String imageFileName;
    private final NutritionInfo nutrition;
    private final List<Ingredient> ingredients;
    private final List<Instruction> instructions;

    public Recipe(String recipeId, String name, String proteinType, int priceRangeMax, String imageFileName, NutritionInfo nutrition, List<Ingredient> ingredients, List<Instruction> instructions) {
        this.recipeId = recipeId;
        this.name = name;
        this.proteinType = proteinType;
        this.priceRangeMax = priceRangeMax;
        this.imageFileName = imageFileName;
        this.nutrition = nutrition;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters for all attributes
    public String getRecipeId() { return recipeId; }
    public String getName() { return name; }
    public String getProteinType() { return proteinType; }
    public int getPriceRangeMax() { return priceRangeMax; }
    public String getImageFileName() { return imageFileName; }
    public NutritionInfo getNutrition() { return nutrition; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public List<Instruction> getInstructions() { return instructions; }
}
