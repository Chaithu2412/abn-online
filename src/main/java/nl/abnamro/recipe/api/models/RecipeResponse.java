package nl.abnamro.recipe.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.Valid;


@Valid
@Getter
@Setter
@NoArgsConstructor
public class RecipeResponse extends Recipe {

    private long recipe_id;

}
