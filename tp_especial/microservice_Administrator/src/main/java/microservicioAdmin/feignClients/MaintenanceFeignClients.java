package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.MaintenanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "microservice_maintenance", url = "http://localhost:8083")
public interface MaintenanceFeignClients {
    @GetMapping("/scooterNotRepaired")
    List<MaintenanceDTO> getMaintenances();
}
