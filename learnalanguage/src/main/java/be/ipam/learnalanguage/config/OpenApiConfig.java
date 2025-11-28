package be.ipam.learnalanguage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI learnALanguageApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Learn A Language API")
                        .description("API REST pour gérer les langues de l'application Learn A Language")
                        .version("1.0.0"));
    }
}
