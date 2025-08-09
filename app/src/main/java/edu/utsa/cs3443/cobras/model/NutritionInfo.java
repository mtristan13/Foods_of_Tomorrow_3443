package edu.utsa.cs3443.cobras.model;
/**
 * Represents the nutritional breakdown for a recipe.
 * This is a pure data class
 */
public class NutritionInfo {
    private final int calories;
    private final int proteinG;
    private final int carbsG;
    private final int fatG;

    public NutritionInfo(int calories, int proteinG, int carbsG, int fatG) {
        this.calories = calories;
        this.proteinG = proteinG;
        this.carbsG = carbsG;
        this.fatG = fatG;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteinG() {
        return proteinG;
    }

    public int getCarbsG() {
        return carbsG;
    }

    public int getFatG() {
        return fatG;
    }
}