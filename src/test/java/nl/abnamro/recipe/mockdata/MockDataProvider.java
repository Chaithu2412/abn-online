package nl.abnamro.recipe.mockdata;

import com.google.gson.JsonObject;
import nl.abnamro.recipe.api.models.RecipeResponse;

public class MockDataProvider {

    public static JsonObject createRecipeRequest(){
        return MockJsonBuilder.aRequest()
                .withProperty("recipe_name", "apple juice")
                .withProperty("no_of_servings", "2")
                .withProperty("recipe_instructions", "mix and drink it")
                .withProperty("vegetarian","true")
                .build();
    }

    public static RecipeResponse getRecipe(){
        RecipeResponse recipeResponse = new RecipeResponse();
        recipeResponse.setRecipe_id(1);
        recipeResponse.setName("apple juice");
        recipeResponse.setInstructions("drink");
        recipeResponse.setServings(2);
        recipeResponse.setVegetarian(true);

        return  recipeResponse;

    }
}