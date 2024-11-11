package org.example.microservicemaintenance.FeignClient;

import org.example.microservicemaintenance.DTOs.MaintenanceReportDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "micro-trip", url = "http://localhost:8085")
public interface TripFeignClient {

    @GetMapping("/api/trips/pauseByScooters")
    List<MaintenanceReportDTO> getAllTripPause();

}
