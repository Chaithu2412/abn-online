package nl.abnamro.recipe.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponse extends Recipe {

    private long recipe_id;

}
