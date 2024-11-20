package com.tpe.micro.scooters.ConfigSwagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "MICROSERVICE SCOOTER",
                description = "Microservicio de Scooter",
                contact = @Contact(
                        name = "Enzo Del Sole"
                ),
                version = "1.0.0"
        )
)
public class Swagger {

}
