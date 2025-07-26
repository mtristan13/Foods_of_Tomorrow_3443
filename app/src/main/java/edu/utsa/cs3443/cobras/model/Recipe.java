package edu.utsa.cs3443.cobras.model;

import java.util.List;

public class Recipe {
    private String title;
    private String category;
    private List<String> ingredients;
    private List<String> steps;
    private boolean isFavorite = false;

    public Recipe(String title, String category, List<String> ingredients, List<String> steps) {
        this.title = title;
        this.category = category;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public List<String> getIngredients() { return ingredients; }
    public List<String> getSteps() { return steps; }
    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) {
        this.isFavorite = favorite;
    }
}
