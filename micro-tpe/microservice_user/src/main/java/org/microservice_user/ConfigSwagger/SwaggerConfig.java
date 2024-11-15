package org.microservice_user.ConfigSwagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "MICROSERVICE USER",
                description = "Microservicio de Usuario que contiene las entidades User y Account",
                contact = @Contact(
                        name = "Lorenzo Fernández",
                        email = "lfernandez@alumnos.exa.unicen.edu.ar"
                ),
                version = "1.0.0"
        )
)
public class SwaggerConfig {}
