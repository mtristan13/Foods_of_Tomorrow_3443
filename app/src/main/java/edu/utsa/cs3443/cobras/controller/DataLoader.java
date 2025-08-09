package edu.utsa.cs3443.cobras.controller;
import android.content.Context;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.utsa.cs3443.cobras.model.Ingredient;
import edu.utsa.cs3443.cobras.model.Instruction;
import edu.utsa.cs3443.cobras.model.NutritionInfo;
import edu.utsa.cs3443.cobras.model.Recipe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles all low-level file I/O operations using a robust CSV parser.
 */
public class DataLoader {

    private static final String FAVORITES_FILENAME = "favorites.csv";
    private static final String TAG = "DataLoader";

    /**
     * Loads all recipes from the recipes.csv file in the assets folder
     * @param context The application context to access assets.
     * @return A list of Recipe objects.
     */
    public List<Recipe> loadRecipesFromCsv(Context context) {
        List<Recipe> recipes = new ArrayList<>();
        try (InputStream is = context.getAssets().open("recipes.csv");
             CSVReader reader = new CSVReader(new InputStreamReader(is))) {

            // Skip the header line
            reader.readNext();

            String[] tokens;
            while ((tokens = reader.readNext()) != null) {
                // OpenCSV correctly handles quoted commas, so tokens[] is reliable.
                if (tokens.length < 11) {
                    Log.w(TAG, "Skipping malformed CSV row.");
                    continue;
                }

                try {
                    String id = tokens[0].trim();
                    String name = tokens[1].trim();
                    String protein = tokens[2].trim();
                    int price = Integer.parseInt(tokens[3].trim());
                    String imageFile = tokens[4].trim();
                    // Nutrition is column 8 (index 8)
                    String[] nutritionTokens = tokens[8].trim().split(",");
                    NutritionInfo nutrition = new NutritionInfo(
                            Integer.parseInt(nutritionTokens[0].trim()),
                            Integer.parseInt(nutritionTokens[1].trim()),
                            Integer.parseInt(nutritionTokens[2].trim()),
                            Integer.parseInt(nutritionTokens[3].trim())
                    );
                    // Ingredients are column 9 (index 9)
                    List<Ingredient> ingredients = parseIngredients(tokens[9].trim());
                    // Instructions are column 10 (index 10)
                    List<Instruction> instructions = parseInstructions(tokens[10].trim());

                    recipes.add(new Recipe(id, name, protein, price, imageFile, nutrition, ingredients, instructions));
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Number format error on CSV row: " + String.join(",", tokens), e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.e(TAG, "Array index error on CSV row (check nutrition format): " + String.join(",", tokens), e);
                }
            }
        } catch (IOException | CsvValidationException e) {
            Log.e(TAG, "Error reading or parsing recipes.csv", e);
        }
        Log.d(TAG, "Successfully loaded " + recipes.size() + " recipes.");
        return recipes;
    }

    private List<Ingredient> parseIngredients(String ingredientsString) {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] items = ingredientsString.split(";");
        for (String item : items) {
            String[] parts = item.split(":");
            if (parts.length == 3) {
                ingredients.add(new Ingredient(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            }
        }
        return ingredients;
    }

    private List<Instruction> parseInstructions(String instructionsString) {
        List<Instruction> instructions = new ArrayList<>();
        String[] steps = instructionsString.split(";");
        for (int i = 0; i < steps.length; i++) {
            instructions.add(new Instruction(i + 1, steps[i].trim()));
        }
        return instructions;
    }

    /**
     * Loads the set of favorited recipe IDs
     * @param context The application context.
     * @return A set of favorited recipe IDs. Returns an empty set if file not found.
     */
    public Set<String> loadFavoritesFromFile(Context context) {
        Set<String> favoriteIds = new HashSet<>();
        try (FileInputStream fis = context.openFileInput(FAVORITES_FILENAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    favoriteIds.add(line.trim());
                }
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG, "favorites.csv not found. This is normal on first run.");
        } catch (IOException e) {
            Log.e(TAG, "Error reading favorites file", e);
        }
        return favoriteIds;
    }

    /**
     * Saves the current set of favorited recipe IDs to internal storage.
     * @param context The application context.
     * @param favoriteIds The set of IDs to save.
     */
    public void saveFavoritesToFile(Context context, Set<String> favoriteIds) {
        try (FileOutputStream fos = context.openFileOutput(FAVORITES_FILENAME, Context.MODE_PRIVATE);
             PrintWriter writer = new PrintWriter(fos)) {
            for (String id : favoriteIds) {
                writer.println(id);
            }
            Log.i(TAG, "Successfully saved " + favoriteIds.size() + " favorites.");
        } catch (IOException e) {
            Log.e(TAG, "Error saving favorites file", e);
        }
    }
}
