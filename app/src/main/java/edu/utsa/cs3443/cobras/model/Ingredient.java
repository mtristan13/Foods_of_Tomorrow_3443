package edu.utsa.cs3443.cobras.model;
/**
 * Represents a single ingredient item for a recipe.
 * This is a pure data class.
 */
public class Ingredient {
    private final String item;
    private final String quantity;
    private final String unit;

    public Ingredient(String item, String quantity, String unit) {
        this.item = item;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getItem() {
        return item;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    /**
     * Provides a formatted string for display
     * @return Formatted ingredient string.
     */
    public String getFormattedIngredient() {
        return quantity + " " + unit + " " + item;
    }
}
