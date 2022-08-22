package nl.abnamro.recipe.service;

import lombok.RequiredArgsConstructor;
import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.api.models.RecipeResponse;
import nl.abnamro.recipe.repository.RecipeRepository;
import nl.abnamro.recipe.repository.Specifications;
import nl.abnamro.recipe.repository.entity.RecipeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeResponse createRecipe(final Recipe recipe) {

        RecipeEntity recipeEntity = recipeRepository.save(MapStructMapper.instance.recipeDTOtoRecipeEntity(recipe));

        return MapStructMapper.instance.recipeEntityToRecipeResponse(recipeEntity);
    }

    @Transactional
    public RecipeResponse getRecipe(final long id) {

        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);

        if (recipeEntity.isPresent()) {
            return MapStructMapper.instance.recipeEntityToRecipeResponse(recipeEntity.get());
        }
        return null;
    }

    public RecipeResponse updateRecipe(final long id, final Recipe recipe) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);

        if (recipeEntity.isPresent()) {
            recipe.setId(id);
            RecipeEntity recipeEntityUpdated = recipeRepository.save(MapStructMapper.instance.recipeDTOtoRecipeEntity(recipe));
            return MapStructMapper.instance.recipeEntityToRecipeResponse(recipeEntityUpdated);
        }

        //fix this with optional or throwing error
        return null;
    }

    public void deleteRecipe(final long id) {

        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);

        if (!recipeEntity.isPresent()) {
            //throw error
            throw new RuntimeException();
        }
        recipeRepository.deleteById(id);
    }

    @Transactional
    public List<RecipeResponse> filterRecipes(boolean isVeg, Integer minServings, String ingredient, String instruction) {

        List<RecipeEntity> filteredEntityList = recipeRepository.findAll(Specifications.recipeType(isVeg)
                .or(Specifications.minServings(minServings))
                .or(Specifications.ingredientIncluded(ingredient))
                .or(Specifications.instructionIncluded(instruction)));

        return MapStructMapper.instance.entityListToResponseList(filteredEntityList);
    }

}
