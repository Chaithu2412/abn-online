package nl.abnamro.recipe.service;


import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.api.models.RecipeResponse;
import nl.abnamro.recipe.exception.RecipeNotFoundException;
import nl.abnamro.recipe.repository.RecipeRepository;
import nl.abnamro.recipe.repository.Specifications;
import nl.abnamro.recipe.repository.entity.IngredientEntity;
import nl.abnamro.recipe.repository.entity.RecipeEntity;
import nl.abnamro.recipe.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RecipeServiceTest {

    @Mock
    RecipeRepository mockRecipeRepository;

    @Mock
    Specifications mockSpecifications;

    Specification<Object> mockSpec;

    @InjectMocks
    RecipeService classUnderTest;

    @Test
    void testCreateRecipe(){
        when(mockRecipeRepository.save(any())).thenReturn(createSampleEntity());
        RecipeResponse actual= classUnderTest.createRecipe(createSampleRecipe());
        assertNotNull(actual);
        assertEquals(true,actual.isVegetarian());
    }

    @Test
    void testGetRecipeWithInvalidId(){
        when(mockRecipeRepository.findById(2L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RecipeNotFoundException.class, () -> {
            classUnderTest.getRecipe(2);
        });
    }

    @Test
    void testGetRecipe(){
        when(mockRecipeRepository.findById(1L)).thenReturn(Optional.of(createSampleEntity()));
        RecipeResponse actual= classUnderTest.getRecipe(1);
        assertNotNull(actual);
        assertEquals("apple juice",actual.getName());
    }

    @Test
    void testUpdateRecipeForInvalidId(){
        when(mockRecipeRepository.findById(2L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RecipeNotFoundException.class, () -> {
            classUnderTest.updateRecipe(2,any());
        });
    }

    @Test
    void testUpdateRecipe(){
        when(mockRecipeRepository.findById(2l)).thenReturn(Optional.of(createSampleEntity()));
        when(mockRecipeRepository.save(any())).thenReturn(createSampleEntity());

        RecipeResponse actual=classUnderTest.updateRecipe(2,createSampleRecipe());
        assertNotNull(actual);
        assertEquals(2,actual.getServings());
    }

    @Test
    void testDeleteRecipeForInvalidId(){
        when(mockRecipeRepository.findById(2L)).thenReturn(Optional.empty());

        Assertions.assertThrows(RecipeNotFoundException.class, () -> {
            classUnderTest.deleteRecipe(2);
        });
    }

    @Test
    void testDeleteRecipe() {
        when(mockRecipeRepository.findById(1l)).thenReturn(Optional.of(createSampleEntity()));
        doNothing().when(mockRecipeRepository).deleteById(1L);
        classUnderTest.deleteRecipe(1);
    }

    @Test
    void testFilterRecipes(){

        List<RecipeEntity> entityList = new ArrayList<>();
        entityList.add(createSampleEntity());

        when(mockRecipeRepository.findAll(Specifications.recipeType(false)
                .or(Specifications.minServings(4))
                .or(Specifications.ingredientIncluded("invalid"))
                .or(Specifications.instructionIncluded("invalid")))).thenReturn(entityList);

        List<RecipeResponse> actual = classUnderTest.filterRecipes(true,2,"apple","drink");
        assertNotNull(actual);
        assertEquals(0,actual.size());
    }

    Recipe createSampleRecipe(){
        return Recipe.builder().name("apple juice").isVegetarian(true).servings(2).build();
    }

    RecipeEntity createSampleEntity(){

        List<IngredientEntity> ingredientList = new ArrayList<>();
        ingredientList.add(IngredientEntity.builder().name("apple").quantity(2).build());

        return RecipeEntity.builder().id(1).name("apple juice").isVegetarian(true).servings(2).instructions("drink").ingredients(ingredientList).build();
    }

}
