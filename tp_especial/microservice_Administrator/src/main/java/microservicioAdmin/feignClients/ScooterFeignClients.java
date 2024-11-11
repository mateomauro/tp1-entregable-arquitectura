package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient(name = "microservice_scooter", url = "http://localhost:8082")
public interface ScooterFeignClients {

    @PostMapping("")
    ScooterDTO insertScooter(@RequestBody ScooterDTO scooterDTO);

    @PutMapping("/update/{idScooter}")
    ScooterDTO updateScooter(@PathVariable long idScooter, @RequestBody ScooterDTO scooterDTO);

    @GetMapping("/actives")
    List<ScooterDTO> getActivs();

    @DeleteMapping("/{id}")
    ScooterDTO deleteScooter(@PathVariable Long id);

    @GetMapping("/{id}")
    ScooterDTO getScooterById(@PathVariable Long id);

    @GetMapping("")
    List<ScooterDTO> findAllScooters();

}