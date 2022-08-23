package nl.abnamro.recipe.service;

import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.api.models.RecipeResponse;
import nl.abnamro.recipe.repository.entity.RecipeEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface MapStructMapper {

    MapStructMapper instance= Mappers.getMapper(MapStructMapper.class);

    RecipeEntity recipeDTOtoRecipeEntity(Recipe recipe);

    List<RecipeResponse> entityListToResponseList(List<RecipeEntity> entityList);

    @Mapping(source="id",target = "recipe_id")
    RecipeResponse recipeEntityToRecipeResponse(RecipeEntity recipeEntity);

}
