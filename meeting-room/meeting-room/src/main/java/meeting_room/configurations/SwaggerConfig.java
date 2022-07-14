package meeting_room.configurations;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI(){

        }
            .info(new Info()
            .title("Meeting_Room")
                        .description("Meeting_Room Service")
                        .version("v0.0.1"));

    }
}
