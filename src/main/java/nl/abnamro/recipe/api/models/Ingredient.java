package nl.abnamro.recipe.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Valid
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ingredient {

    @NotBlank
    private String name;
    @NotBlank
    private int quantity;
}
