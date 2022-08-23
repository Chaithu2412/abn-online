package nl.abnamro.recipe.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("ABN Amro Recipe Services")
                        .description("This api creates, fetches, updates, deletes and filter the recipes")
                        .version("1"));
    }
}
