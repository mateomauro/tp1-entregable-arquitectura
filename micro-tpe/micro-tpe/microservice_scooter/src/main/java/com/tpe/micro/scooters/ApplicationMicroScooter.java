package com.tpe.micro.scooters;

import com.tpe.micro.scooters.Utils.LoadDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ApplicationMicroScooter {
		@Autowired
		private LoadDatabase loadDatabase;

		public static void main(String[] args) {
		SpringApplication.run(ApplicationMicroScooter.class, args);
	}

    @PostConstruct
    public void init() throws Exception {
        loadDatabase.loadScooters();
    }
}
