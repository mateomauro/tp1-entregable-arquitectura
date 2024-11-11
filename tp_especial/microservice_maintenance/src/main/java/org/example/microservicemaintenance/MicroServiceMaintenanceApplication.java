package org.example.microservicemaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "org/example/microservicemaintenance/Entities")
//@ComponentScan(basePackages = "Controller")
public class MicroServiceMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceMaintenanceApplication.class, args);
        System.out.println("anda");
    }

}
