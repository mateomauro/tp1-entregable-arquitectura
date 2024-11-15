package microservicioAdmin;

import jakarta.annotation.PostConstruct;
import microservicioAdmin.Utils.LoadDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AdminApplication {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        loadDatabase.loadAdminRate();
    }
}