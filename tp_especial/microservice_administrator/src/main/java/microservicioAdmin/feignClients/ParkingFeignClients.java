package microservicioAdmin.feignClients;

import microservicioAdmin.feignClients.model.ParkingDTO;
import microservicioAdmin.feignClients.model.PauseDTO;
import microservicioAdmin.feignClients.model.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "micro-parking", url = "http://localhost:8084")
public interface ParkingFeignClients {
    @PostMapping("/api/parkings")
    ParkingDTO insertParking(@RequestBody ParkingDTO parkingDTO);

    @PutMapping("/api/parkings/{id_parking}")
    ParkingDTO updateParking(@PathVariable Long id_parking , @RequestBody ParkingDTO parkingDTO);

    @DeleteMapping("/api/parkings/{id_parking}")
    ParkingDTO deleteParking(@PathVariable long id_parking);

    @GetMapping("/api/parkings")
    List<ParkingDTO> getAllParkings();

    @GetMapping("/api/parkings/parkingById/{id}")
    ParkingDTO getById(@PathVariable Long id);

}
