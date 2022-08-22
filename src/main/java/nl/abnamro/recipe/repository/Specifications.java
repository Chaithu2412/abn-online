package nl.abnamro.recipe.repository;

import nl.abnamro.recipe.repository.entity.IngredientEntity;
import nl.abnamro.recipe.repository.entity.RecipeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public interface Specifications {

    public static Specification<RecipeEntity> recipeType(boolean isVeg){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isVegetarian"),isVeg);
    }

    public static Specification<RecipeEntity> minServings(Integer minServings){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("servings"),minServings);
    }

    public static Specification<RecipeEntity> ingredientIncluded(String ingredient) {
        return (root, query, criteriaBuilder) -> {
            Join<RecipeEntity, IngredientEntity> ingredientEntityJoin = root.join("ingredients");
            return criteriaBuilder.like(ingredientEntityJoin.get("name"), "%" + ingredient + "%");
        };
    }

    public static Specification<RecipeEntity> instructionIncluded( String instruction) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("instructions"), "%" + instruction + "%");
    }
}
