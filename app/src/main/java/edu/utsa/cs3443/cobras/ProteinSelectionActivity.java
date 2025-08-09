package edu.utsa.cs3443.cobras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.cobras.R;

public class ProteinSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protein_selection);

        Button beefButton = findViewById(R.id.beefButton);
        Button porkButton = findViewById(R.id.porkButton);
        Button chickenButton = findViewById(R.id.chickenButton);
        Button fishButton = findViewById(R.id.fishButton);
        Button favoritesButton = findViewById(R.id.favoritesButton);

        beefButton.setOnClickListener(v -> navigateToBudget("beef"));
        porkButton.setOnClickListener(v -> navigateToBudget("pork"));
        chickenButton.setOnClickListener(v -> navigateToBudget("chicken"));
        fishButton.setOnClickListener(v -> navigateToBudget("fish"));

        favoritesButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecipeListActivity.class);
            intent.putExtra("FILTER_TYPE", "favorites");
            startActivity(intent);
        });
    }

    private void navigateToBudget(String proteinType) {
        Intent intent = new Intent(this, BudgetSelectionActivity.class);
        intent.putExtra("PROTEIN_TYPE", proteinType);
        startActivity(intent);
    }
}