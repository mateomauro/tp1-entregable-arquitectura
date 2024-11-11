package org.example.microservicemaintenance.FeignClient;

import org.example.microservicemaintenance.FeignClient.Model.Scooter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "micro_scooter", url = "http://localhost:8082")
public interface ScooterFeignClient {

    @GetMapping("/api/scooters")
    public List<Scooter> getAllScooter();

    @GetMapping("/api/scooters/{id}/toMaintenance")
    public Scooter getScooterMaintenance(@PathVariable long idScooter);

}
