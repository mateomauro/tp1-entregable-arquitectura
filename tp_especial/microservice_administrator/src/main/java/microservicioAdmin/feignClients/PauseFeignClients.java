package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.PauseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@FeignClient(name = "micro-trip", url = "http://localhost:8085")

public interface PauseFeignClients {

    @GetMapping("/api/trips/pauseByIdTrip/trip/{id_trip}")
    List<PauseDTO> getAllPauseByIdTrip(@PathVariable long id_trip);
}
