package com.pa.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "AddressCorrection", version = "0.0.1-SNAPSHOT"),
        servers = {@Server(url = "http://localhost:8082")},
        tags = {
                @Tag(
                        name = "AddressCorrection",
                        description = "This is the main endpoint of the application, where address correction requests can be made"
                )
        }
)
public class OpenApiConfig {
}
