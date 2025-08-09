package edu.utsa.cs3443.cobras.controller;
import android.content.Context;
import java.util.Set;

/**
 * Manages the state of favorited recipes and handles the File I/O for the favorites list.
 */
public class FavoritesManager {
    private final Set<String> favoritedRecipeIds;
    private final DataLoader dataLoader;

    public FavoritesManager(Context context) {
        this.dataLoader = new DataLoader();
        this.favoritedRecipeIds = dataLoader.loadFavoritesFromFile(context);
    }

    public boolean isFavorite(String recipeId) {
        return favoritedRecipeIds.contains(recipeId);
    }

    public void addFavorite(String recipeId) {
        favoritedRecipeIds.add(recipeId);
    }

    public void removeFavorite(String recipeId) {
        favoritedRecipeIds.remove(recipeId);
    }

    /**
     * Persists the current list of favorites to a file.
     * @param context The application context.
     */
    public void saveFavorites(Context context) {
        dataLoader.saveFavoritesToFile(context, favoritedRecipeIds);
    }

    public Set<String> getFavoritedRecipeIds() {
        return favoritedRecipeIds;
    }
}
