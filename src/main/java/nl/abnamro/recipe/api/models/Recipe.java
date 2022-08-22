package nl.abnamro.recipe.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {

    @JsonIgnore
    private long id;

    @NotBlank
    @JsonProperty("recipe_name")
    private String name;

    private boolean isVegetarian;

    private List<Ingredient> ingredients;

    @NotBlank
    @JsonProperty("recipe_instructions")
    private String instructions;

    @JsonProperty("no_of_servings")
    private int servings;

}
