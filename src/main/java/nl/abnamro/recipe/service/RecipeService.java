package nl.abnamro.recipe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.api.models.RecipeResponse;
import nl.abnamro.recipe.exception.RecipeNotFoundException;
import nl.abnamro.recipe.repository.RecipeRepository;
import nl.abnamro.recipe.repository.Specifications;
import nl.abnamro.recipe.repository.entity.RecipeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeResponse createRecipe(final Recipe recipe) {

        var recipeEntity = recipeRepository.save(this.recipeDtoToRecipeEntity(recipe));

        log.debug("recipe created successfully for :{}",recipeEntity.getId());
        return this.recipeEntityToRecipeResponse(recipeEntity);
    }

    @Transactional
    public RecipeResponse getRecipe(final long id) {

        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);

        if (!recipeEntity.isPresent()) {
            log.error("recipe not found in database");
           throw new RecipeNotFoundException();
        }
        return this.recipeEntityToRecipeResponse(recipeEntity.get());
    }

    public RecipeResponse updateRecipe(final long id, final Recipe recipe) {
        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);

        if (!recipeEntity.isPresent()) {
            log.error("recipe not found in database");
            throw new RecipeNotFoundException();
        }
        recipe.setId(id);
        RecipeEntity recipeEntityUpdated = recipeRepository.save(this.recipeDtoToRecipeEntity(recipe));
        return this.recipeEntityToRecipeResponse(recipeEntityUpdated);
    }

    public void deleteRecipe(final long id) {

        Optional<RecipeEntity> recipeEntity = recipeRepository.findById(id);

        if (!recipeEntity.isPresent()) {
            log.error("recipe not found in database");
            throw new RecipeNotFoundException();
        }
        recipeRepository.deleteById(id);
    }

    @Transactional
    public List<RecipeResponse> filterRecipes(final boolean isVeg, final Integer minServings,
                                              final String ingredient, final String instruction) {
        List<RecipeEntity> filteredEntityList = recipeRepository.findAll(Specifications.recipeType(isVeg)
                .or(Specifications.minServings(minServings))
                .or(Specifications.ingredientIncluded(ingredient))
                .or(Specifications.instructionIncluded(instruction)));
        return MapStructMapper.instance.entityListToResponseList(filteredEntityList);
    }

    private RecipeEntity recipeDtoToRecipeEntity(final Recipe recipe){
       return MapStructMapper.instance.recipeDTOtoRecipeEntity(recipe);
    }

    private RecipeResponse recipeEntityToRecipeResponse(final RecipeEntity recipeEntity){
        return MapStructMapper.instance.recipeEntityToRecipeResponse(recipeEntity);
    }


}
