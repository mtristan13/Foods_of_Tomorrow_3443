package edu.utsa.cs3443.cobras.model;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealapp.R;

public class MainIngredient extends AppCompatActivity {

    private Button btnChicken, btnBeef, btnPork, btnFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnChicken = findViewById(R.id.btnChicken);
        btnBeef = findViewById(R.id.btnBeef);
        btnPork = findViewById(R.id.btnPork);
        btnFavorites = findViewById(R.id.btnFavorites);

        View.OnClickListener clickListener = v -> {
            String category = ((Button) v).getText().toString().toLowerCase();
            Intent intent = new Intent(MainMenuActivity.this, RecipeListActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        };

        btnChicken.setOnClickListener(clickListener);
        btnBeef.setOnClickListener(clickListener);
        btnPork.setOnClickListener(clickListener);
        btnFavorites.setOnClickListener(clickListener);
    }
}
