package org.example.microservice_trip;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTripApplication.class, args);
		System.out.println("anda");
	}

}
