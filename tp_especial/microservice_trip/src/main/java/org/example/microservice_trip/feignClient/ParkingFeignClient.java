package org.example.microservice_trip.feignClient;

import org.example.microservice_trip.dtos.ParkingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="micro-parking",url="http://localhost:8084")
public interface ParkingFeignClient {

    @GetMapping("/api/parkings")
    List<ParkingDto> findAll();

    @GetMapping("/api/parkings/parkingByLatitudeAndLongitude/{latitude}/{longitude}")
    ParkingDto getParkingByLatitudeAndLongitude(@PathVariable double latitude, @PathVariable double longitude);

    @GetMapping("/api/parkings/parkingById/{id_parking}")
    ParkingDto getParkingById(@PathVariable long id_parking);
}
