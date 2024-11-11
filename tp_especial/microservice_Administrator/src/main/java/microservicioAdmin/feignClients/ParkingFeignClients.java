package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.ParkingDTO;
import microservicioAdmin.feignClients.model.PauseDTO;
import microservicioAdmin.feignClients.model.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice_parking", url = "http://localhost:8084")
public interface ParkingFeignClients {
    @PutMapping("")
    ParkingDTO insertParking(@RequestBody ParkingDTO parkingDTO);

    @PutMapping("/update/id_parking")
    ParkingDTO updateParking(@PathVariable Long idParking ,ParkingDTO parkingDTO);

    @DeleteMapping("/delete/{id_paraking}")
    ScooterDTO deleteParking(@PathVariable long idParking);

    @GetMapping("")
    List<ParkingDTO> getAllParkings();

    @GetMapping("/parking/{id}")
    ParkingDTO getById(Long idParking);

}
