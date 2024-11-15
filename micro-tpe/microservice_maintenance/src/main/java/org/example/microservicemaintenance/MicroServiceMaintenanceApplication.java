package org.example.microservicemaintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MicroServiceMaintenanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceMaintenanceApplication.class, args);
        System.out.println("anda");
    }

}
