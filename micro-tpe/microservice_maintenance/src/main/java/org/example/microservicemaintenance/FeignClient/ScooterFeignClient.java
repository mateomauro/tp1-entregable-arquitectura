package org.example.microservicemaintenance.FeignClient;

import org.example.microservicemaintenance.FeignClient.Model.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "micro-scooter")
public interface ScooterFeignClient {

    @GetMapping("/api/scooters")
    List<Scooter> getAllScooter();

    @GetMapping("/api/scooters/{id}/toMaintenance")
    Scooter disableScooterMaintenance(@PathVariable long id);

    @GetMapping("/api/scooters/{id_scooter}/leaveMaintenance")
    Scooter leaveMaintenance(@PathVariable Long id_scooter);
}