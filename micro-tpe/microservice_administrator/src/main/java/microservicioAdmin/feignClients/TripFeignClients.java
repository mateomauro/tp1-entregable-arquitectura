package microservicioAdmin.feignClients;
import microservicioAdmin.feignClients.model.PauseDTO;
import microservicioAdmin.feignClients.model.TripDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "micro-trip")
public  interface TripFeignClients {

    @GetMapping("/api/trips/tripsById/{id_trip}")
    TripDTO getTripById(@PathVariable long id_trip);

    //lista de monopatines por x cantidad de viajes y año dado
    @GetMapping("/api/trips/tripByYearAndCountTrip/{year}/{count}")
    List<TripDTO> tripByYearAndCountTrip(@PathVariable int year, @PathVariable int count);
}


