package org.microservice_user.feignClients;


import org.microservice_user.feignClients.model.PauseDto;
import org.microservice_user.feignClients.model.TripDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "micro-trip")
public interface TripFeignClient {

    @PostMapping("/api/trips/startTrip/user/{id_user}/account/{id_account}/scooter/{id_scooter}")
    TripDTO startTrip(@PathVariable Long id_user, @PathVariable Long id_account, @PathVariable Long id_scooter);

    @PutMapping("/api/trips/endTrip/user/{id_user}/account/{id_account}")
    TripDTO endTrip(@PathVariable Long id_user, @PathVariable Long id_account);

    @PostMapping("/api/trips/pauseTrip/user/{id_user}/account/{id_account}")
    PauseDto pauseTrip(@PathVariable Long id_user, @PathVariable Long id_account);

    @PutMapping("/api/trips/unpauseTrip/user/{id_user}/account/{id_account}")
    PauseDto unpauseTrip(@PathVariable Long id_user, @PathVariable Long id_account);
}
