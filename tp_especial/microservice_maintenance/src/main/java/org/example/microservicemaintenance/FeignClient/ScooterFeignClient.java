package org.example.microservicemaintenance.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "microservice_scooters", url = "http://localhost:8081")
public interface ScooterFeignClient {

    //@GetMapping("/api/scooters")
    //public List<ScooterD> getAllScooter()

    //@GetMapping("/api/scooters/{id}/toMaintenance")
    //public scooterResposeDto getScooterMaintenance(@PathVariable long idScooter)

}
