package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.MaintenanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "micro-maintenance")
public interface MaintenanceFeignClients {
    @GetMapping("/api/maintenances/scooterNotRepaired")
    List<MaintenanceDTO> getMaintenances();
}
