import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.mealapp.R;
//import com.example.mealapp.models.Recipe;
//import com.example.mealapp.utils.FavoritesManager;
//import com.example.mealapp.utils.RecipeData;

import java.util.List;

public class RecipeDetail extends AppCompatActivity {

    private TextView titleText, ingredientsText, stepsText;
    private Button favoriteButton;
    private Recipe recipe;
    private String category;
    private int recipeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        titleText = findViewById(R.id.titleTextView);
        ingredientsText = findViewById(R.id.ingredientsTextView);
        stepsText = findViewById(R.id.stepsTextView);
        favoriteButton = findViewById(R.id.favoriteButton);

        category = getIntent().getStringExtra("category");
        recipeIndex = getIntent().getIntExtra("recipeIndex", 0);

        List<Recipe> recipeList;
        if (category.equals("favorited meals") || category.equals("favorites")) {
            recipeList = FavoritesManager.getFavorites();
        } else {
            recipeList = RecipeData.getRecipesByCategory(category);
        }

        recipe = recipeList.get(recipeIndex);

        titleText.setText(recipe.getTitle());
        ingredientsText.setText(formatList("• ", recipe.getIngredients()));
        stepsText.setText(formatList("Step ", recipe.getSteps()));

        updateFavoriteButton();

        favoriteButton.setOnClickListener(v -> {
            recipe.setFavorite(!recipe.isFavorite());
            if (recipe.isFavorite()) {
                FavoritesManager.addFavorite(recipe);
            } else {
                FavoritesManager.removeFavorite(recipe);
            }
            updateFavoriteButton();
        });
    }

    private void updateFavoriteButton() {
        if (recipe.isFavorite()) {
            favoriteButton.setText("★ Remove Favorite");
        } else {
            favoriteButton.setText("☆ Add to Favorites");
        }
    }

    private String formatList(String prefix, List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (prefix.equals("Step ")) {
                sb.append(prefix).append(i + 1).append(": ").append(items.get(i)).append("\n\n");
            } else {
                sb.append(prefix).append(items.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
