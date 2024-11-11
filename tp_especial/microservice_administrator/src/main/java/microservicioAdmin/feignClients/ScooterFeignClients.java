package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient(name = "micro-scooter", url = "http://localhost:8082")
public interface ScooterFeignClients {

    @PostMapping("/api/scooters")
    ScooterDTO insertScooter(@RequestBody ScooterDTO scooterDTO);

    @PutMapping("/api/scooters/update/{idScooter}")
    ScooterDTO updateScooter(@PathVariable long idScooter, @RequestBody ScooterDTO scooterDTO);

    @GetMapping("/api/scooters/actives")
    List<ScooterDTO> getActivs();

    @DeleteMapping("/api/scooters/{id}")
    ScooterDTO deleteScooter(@PathVariable Long id);

    @GetMapping("/api/scooters/{id}")
    ScooterDTO getScooterById(@PathVariable Long id);

    @GetMapping("/api/scooters")
    List<ScooterDTO> findAllScooters();

}