package nl.abnamro.recipe.api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.api.models.RecipeResponse;
import nl.abnamro.recipe.service.RecipeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping(value = "/recipe", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> createRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.createRecipe(recipe));
    }

    @GetMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> getRecipe(@PathVariable long id) {
        log.info("getting recipe by id: {}",id);
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PutMapping(value = "/recipe/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecipeResponse> updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe){
        log.info("updating the recipe: {}",id);
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipe));
    }

    @DeleteMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  deleteRecipe(@PathVariable long id){
        log.info("deleting the recipe: {}",id);
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value="/recipe/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RecipeResponse>> searchRecipes(@RequestParam(name="isVeg", required=false) boolean isVeg,
                                                              @RequestParam(name="min_servings", required=false) Integer min_servings,
                                                              @RequestParam(name="ingredientIncluded", required=false) String ingredientIncluded,
                                                              @RequestParam(name="instruction", required=false) String instruction){
         log.info("searching the recipes based on isVeg={}, min_servings={}, ingredientIncluded={}, instruction={}"
                 ,isVeg,min_servings,ingredientIncluded,instruction);
         return ResponseEntity.ok(recipeService.filterRecipes(isVeg,min_servings,ingredientIncluded,instruction));

    }

}