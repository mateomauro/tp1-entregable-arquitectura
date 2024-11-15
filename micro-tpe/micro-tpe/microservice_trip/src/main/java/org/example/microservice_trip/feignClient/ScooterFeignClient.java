package org.example.microservice_trip.feignClient;

import org.example.microservice_trip.feignClient.model.ScooterDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="micro-scooter")
public interface ScooterFeignClient {

    @GetMapping("/api/scooters/{id}")
    ScooterDto getScooterById(@PathVariable Long id);

    @PutMapping("/api/scooters/{id}")
    ScooterDto updateScooter(@PathVariable Long id, @RequestBody ScooterDto scooterDto);
}
