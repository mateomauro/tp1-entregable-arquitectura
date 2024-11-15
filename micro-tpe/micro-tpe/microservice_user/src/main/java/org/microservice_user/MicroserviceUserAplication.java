package org.microservice_user;

import jakarta.annotation.PostConstruct;
import org.microservice_user.Utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MicroserviceUserAplication {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceUserAplication.class, args);
        System.out.printf("Esta andando");
    }

    @PostConstruct
    public void init() throws Exception {
        loadDatabase.loadUsers();
    }
}