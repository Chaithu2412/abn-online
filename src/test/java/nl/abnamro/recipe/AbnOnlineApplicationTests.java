package nl.abnamro.recipe;

import nl.abnamro.recipe.api.models.Recipe;
import nl.abnamro.recipe.api.models.RecipeResponse;
import nl.abnamro.recipe.repository.entity.RecipeEntity;
import nl.abnamro.recipe.service.MapStructMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AbnOnlineApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMapstruct(){

		RecipeEntity recipe = new RecipeEntity();
		recipe.setId(5);
		recipe.setName("pototo");

		RecipeResponse recipeResponse = MapStructMapper.instance.recipeEntityToRecipeResponse(recipe);


		assertNotNull(recipeResponse.getId());

		System.out.println(recipeResponse.getName());
		System.out.println(recipeResponse.getId());

		System.out.println("".toLowerCase());

	}


}
