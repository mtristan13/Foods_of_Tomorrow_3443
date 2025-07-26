package edu.utsa.cs3443.cobras.model;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RecipeList extends AppCompatActivity {

    private ListView recipeListView;
    private List<Recipe> recipes;
    private List<String> recipeTitles;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recipeListView = findViewById(R.id.recipeListView);
        category = getIntent().getStringExtra("category");

        // Get recipes for the selected category
        if (category.equals("favorited meals") || category.equals("favorites")) {
            recipes = FavoritesManager.getFavorites();
        } else {
            recipes = RecipeData.getRecipesByCategory(category);
        }

        // Convert recipes to titles for display
        recipeTitles = new ArrayList<>();
        for (Recipe r : recipes) {
            recipeTitles.add(r.getTitle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, recipeTitles);
        recipeListView.setAdapter(adapter);

        recipeListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, RecipeDetailActivity.class);
            intent.putExtra("category", category);
            intent.putExtra("recipeIndex", position);
            startActivity(intent);
        });
    }
}
