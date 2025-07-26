package edu.utsa.cs3443.cobras.model;

import java.util.List;

public class Recipe {
    private String title;
    private List<String> ingredients;
    private List<String> steps;
    private boolean isFavorite;

    public Recipe(String title, List<String> ingredients, List<String> steps) {
        this.title = title;
        this.ingredients = ingredients;
        this.steps = steps;
        this.isFavorite = false;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
