package org.microservice_user;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.microservice_user.Utils.LoadDatabase;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Main {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.printf("Esta andando");
    }

    @PostConstruct
    public void init() throws Exception {
        loadDatabase.loadUsers();
    }
}