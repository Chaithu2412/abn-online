package nl.abnamro.recipe.controllers;

import nl.abnamro.recipe.api.controllers.RecipeController;
import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.mockdata.MockDataProvider;
import nl.abnamro.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc()
public class RecipeControllerTest {

    @Mock
    RecipeService mockRecipeService;

    private MockMvc mockMvc;


    private static final String URI = "/api/recipe";

    @BeforeEach
    public void setup(){
        RecipeController recipeController = new RecipeController(mockRecipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void testCreateRecipe() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.post(URI)
                        .content(MockDataProvider.createRecipeRequest().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(mockRecipeService, times(1)).createRecipe(any());
    }

    @Test
    void testGetRecipe() throws Exception{
        when(mockRecipeService.getRecipe(1)).thenReturn(MockDataProvider.getRecipe());
        mockMvc
                .perform(MockMvcRequestBuilders.get(URI+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(mockRecipeService, times(1)).getRecipe(1);
    }

    @Test
    void testUpdateRecipe() throws Exception{

        when(mockRecipeService.updateRecipe(anyLong(),any(Recipe.class))).thenReturn(MockDataProvider.getRecipe());
        mockMvc
                .perform(MockMvcRequestBuilders.put(URI+"/1")
                        .content(MockDataProvider.createRecipeRequest().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockRecipeService, times(1)).updateRecipe(anyLong(),any(Recipe.class));
    }

    @Test
    void testDeleteRecipe() throws Exception{

        mockMvc
                .perform(MockMvcRequestBuilders.delete(URI+"/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        verify(mockRecipeService, times(1)).deleteRecipe(1);
    }

    @Test
    void testSearchRecipes() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get(URI+"/search?min_servings=2&ingredientIncluded=apple&instruction=Drink&isVeg=True")
                        .content(MockDataProvider.createRecipeRequest().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(mockRecipeService,times(1)).filterRecipes(anyBoolean(),anyInt(),anyString(),anyString());
    }
}
