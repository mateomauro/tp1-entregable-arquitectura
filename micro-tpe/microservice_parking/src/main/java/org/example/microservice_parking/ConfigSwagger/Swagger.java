package org.example.microservice_parking.ConfigSwagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "MICROSERVICE PARKING",
                description = "Microservicio de Parking",
                contact = @Contact(
                        name = "Mateo Mauro"
                ),
                version = "1.0.0"
        )
)
public class Swagger {

}
