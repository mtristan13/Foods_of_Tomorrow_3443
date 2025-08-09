package edu.utsa.cs3443.cobras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.cobras.R;

public class BudgetSelectionActivity extends AppCompatActivity {

    private String proteinType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_selection);

        // Retrieve the protein type passed from the previous activity
        proteinType = getIntent().getStringExtra("PROTEIN_TYPE");

        Button budget10Button = findViewById(R.id.budget10Button);
        Button budget15Button = findViewById(R.id.budget15Button);
        Button budget20Button = findViewById(R.id.budget20Button);

        budget10Button.setOnClickListener(v -> navigateToRecipeList(10));
        budget15Button.setOnClickListener(v -> navigateToRecipeList(15));
        budget20Button.setOnClickListener(v -> navigateToRecipeList(20));
    }

    private void navigateToRecipeList(int maxPrice) {
        Intent intent = new Intent(this, RecipeListActivity.class);
        intent.putExtra("FILTER_TYPE", "protein_price");
        intent.putExtra("PROTEIN_TYPE", proteinType);
        intent.putExtra("MAX_PRICE", maxPrice);
        startActivity(intent);
    }
}