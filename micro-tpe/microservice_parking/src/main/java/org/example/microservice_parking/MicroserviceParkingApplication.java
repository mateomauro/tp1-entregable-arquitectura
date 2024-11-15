package org.example.microservice_parking;

import jakarta.annotation.PostConstruct;
import org.example.microservice_parking.Utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MicroserviceParkingApplication {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceParkingApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        loadDatabase.loadParkings();
    }
}
