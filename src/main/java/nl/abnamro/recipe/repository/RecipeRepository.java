package nl.abnamro.recipe.repository;

import nl.abnamro.recipe.repository.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity,Long>, JpaSpecificationExecutor<RecipeEntity> {

    RecipeEntity findRecipeById(int id);

    List<RecipeEntity> findByIsVegetarianAndServingsGreaterThanEqual(boolean Isveg, int servings);

}
