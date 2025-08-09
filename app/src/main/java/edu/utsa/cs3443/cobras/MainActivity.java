package edu.utsa.cs3443.cobras;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import edu.utsa.cs3443.cobras.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectProteinButton = findViewById(R.id.selectProteinButton);

        selectProteinButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProteinSelectionActivity.class);
            startActivity(intent);
        });
    }
}