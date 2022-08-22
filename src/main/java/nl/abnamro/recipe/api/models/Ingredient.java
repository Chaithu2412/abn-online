package nl.abnamro.recipe.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @NotBlank
    private String name;
    @NotBlank
    private int quantity;
}
