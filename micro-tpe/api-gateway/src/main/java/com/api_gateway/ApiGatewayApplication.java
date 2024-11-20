package com.api_gateway;

import com.api_gateway.Utils.LoadDatabase;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
	@Autowired
	private LoadDatabase loadDatabase;

	public static void main(String[] args) { SpringApplication.run(ApiGatewayApplication.class, args); }

	@PostConstruct
    public void init() throws Exception {
        loadDatabase.loadAuthorities();
    }
}
