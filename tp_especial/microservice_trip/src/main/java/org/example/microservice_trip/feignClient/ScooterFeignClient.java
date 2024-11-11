package org.example.microservice_trip.feignClient;

import org.example.microservice_trip.dtos.ScooterDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;



@FeignClient(name="micro_scooter",url="http://localhost:8082")
public interface ScooterFeignClient {

    @GetMapping("/api/scooters/{id}")
    ScooterDto getScooterById(@PathVariable long id);
}
