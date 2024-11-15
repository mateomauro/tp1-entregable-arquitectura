package org.microservice_user.feignClients;

import org.microservice_user.feignClients.model.ScooterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="micro-scooter")
public interface ScooterFeignClient {

    @GetMapping("/api/scooters/getScootersNearby/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
    List<ScooterDTO> getScooterNearby(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double radius);

    @GetMapping("/api/scooters/getScooterNearest/latitude/{latitude}/longitude/{longitude}")
    ScooterDTO getScooterNearest(@PathVariable Double latitude, @PathVariable Double longitude);
}


