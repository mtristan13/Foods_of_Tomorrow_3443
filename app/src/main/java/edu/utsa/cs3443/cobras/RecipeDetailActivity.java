package edu.utsa.cs3443.cobras;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.cobras.R;
import edu.utsa.cs3443.cobras.controller.FavoritesManager;
import edu.utsa.cs3443.cobras.controller.RecipeManager;
import edu.utsa.cs3443.cobras.model.Ingredient;
import edu.utsa.cs3443.cobras.model.Instruction;
import edu.utsa.cs3443.cobras.model.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecipeManager recipeManager;
    private FavoritesManager favoritesManager;
    private Recipe currentRecipe;

    private ImageView recipeImageView;
    private TextView recipeNameTextView;
    private TextView ingredientsTextView;
    private TextView instructionsTextView;
    private TextView nutritionTextView;
    private ImageButton favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipeManager = RecipeManager.getInstance(this);
        favoritesManager = new FavoritesManager(this);

        String recipeId = getIntent().getStringExtra("RECIPE_ID");
        currentRecipe = recipeManager.getRecipeById(recipeId);

        recipeImageView = findViewById(R.id.recipeImageView);
        recipeNameTextView = findViewById(R.id.recipeNameTextView);
        ingredientsTextView = findViewById(R.id.ingredientsTextView);
        instructionsTextView = findViewById(R.id.instructionsTextView);
        nutritionTextView = findViewById(R.id.nutritionTextView);
        favoriteButton = findViewById(R.id.favoriteButton);

        if (currentRecipe != null) {
            populateUI();
            setupFavoriteButton();
        }
    }

    private void populateUI() {
        recipeNameTextView.setText(currentRecipe.getName());

        String imageName = currentRecipe.getImageFileName().replaceAll("\\.(png|jpeg|jpg)$", "");
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        if (imageResId != 0) {
            recipeImageView.setImageResource(imageResId);
        }

        StringBuilder ingredientsBuilder = new StringBuilder();
        for (Ingredient ingredient : currentRecipe.getIngredients()) {
            ingredientsBuilder.append("• ").append(ingredient.getFormattedIngredient()).append("\n");
        }
        ingredientsTextView.setText(ingredientsBuilder.toString());

        StringBuilder instructionsBuilder = new StringBuilder();
        for (Instruction instruction : currentRecipe.getInstructions()) {
            instructionsBuilder.append(instruction.getFormattedInstruction()).append("\n\n");
        }
        instructionsTextView.setText(instructionsBuilder.toString());

        String nutritionInfo = "Calories: " + currentRecipe.getNutrition().getCalories() +
                "\nProtein: " + currentRecipe.getNutrition().getProteinG() + "g" +
                "\nCarbs: " + currentRecipe.getNutrition().getCarbsG() + "g" +
                "\nFat: " + currentRecipe.getNutrition().getFatG() + "g";
        nutritionTextView.setText(nutritionInfo);
    }

    private void setupFavoriteButton() {
        updateFavoriteButtonState();

        favoriteButton.setOnClickListener(v -> {
            if (favoritesManager.isFavorite(currentRecipe.getRecipeId())) {
                favoritesManager.removeFavorite(currentRecipe.getRecipeId());
            } else {
                favoritesManager.addFavorite(currentRecipe.getRecipeId());
            }
            favoritesManager.saveFavorites(this);
            updateFavoriteButtonState();
        });
    }

    private void updateFavoriteButtonState() {
        if (favoritesManager.isFavorite(currentRecipe.getRecipeId())) {
            favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }
}