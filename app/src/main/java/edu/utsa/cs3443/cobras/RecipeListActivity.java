package edu.utsa.cs3443.cobras;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.utsa.cs3443.cobras.R;
import edu.utsa.cs3443.cobras.controller.FavoritesManager;
import edu.utsa.cs3443.cobras.controller.RecipeManager;
import edu.utsa.cs3443.cobras.model.Recipe;
import edu.utsa.cs3443.cobras.adapters.RecipeAdapter;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private RecipeManager recipeManager;
    private FavoritesManager favoritesManager;
    private RecyclerView recyclerView;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recipeManager = RecipeManager.getInstance(this);
        favoritesManager = new FavoritesManager(this);

        recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Recipe> recipesToShow = getRecipesFromIntent();
        adapter = new RecipeAdapter(recipesToShow);
        recyclerView.setAdapter(adapter);
    }

    private List<Recipe> getRecipesFromIntent() {
        String filterType = getIntent().getStringExtra("FILTER_TYPE");

        if ("favorites".equals(filterType)) {
            return recipeManager.getFavoritedRecipes(favoritesManager.getFavoritedRecipeIds());
        } else {
            String protein = getIntent().getStringExtra("PROTEIN_TYPE");
            int maxPrice = getIntent().getIntExtra("MAX_PRICE", 0);
            return recipeManager.getRecipesByFilter(protein, maxPrice);
        }
    }
}