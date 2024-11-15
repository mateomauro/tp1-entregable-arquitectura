package org.example.microservice_trip;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MicroserviceTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTripApplication.class, args);
		System.out.println("anda");
	}

}
