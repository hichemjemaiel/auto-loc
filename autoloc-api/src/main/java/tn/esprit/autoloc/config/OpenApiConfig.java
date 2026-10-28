package tn.esprit.autoloc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI autoLocOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AutoLoc REST API")
                        .description("Documentation des endpoints de l'application AutoLoc (Gestion d'agences de location de véhicules)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("AutoLoc Team")
                                .email("contact@autoloc.tn")));
    }
}

