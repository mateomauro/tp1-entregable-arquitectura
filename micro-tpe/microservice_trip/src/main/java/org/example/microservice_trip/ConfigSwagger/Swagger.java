package org.example.microservice_trip.ConfigSwagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "MICROSERVICE TRIP",
                description = "Microservicio de Trip",
                contact = @Contact(
                        name = "Mateo Mauro"
                ),
                version = "1.0.0"
        )
)
public class Swagger {

}
