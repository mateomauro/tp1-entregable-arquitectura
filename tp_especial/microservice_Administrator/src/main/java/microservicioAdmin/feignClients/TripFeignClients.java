package microservicioAdmin.feignClients;
import microservicioAdmin.feignClients.model.PauseDTO;
import microservicioAdmin.feignClients.model.TripDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "microservice_trip", url = "http://localhost:8085")

public  interface TripFeignClients {

    @GetMapping("/{id_trip}")
    TripDTO getTripById(@PathVariable long id);

    //lista de monopatines por x cantidad de viajes y año dado
    @GetMapping("/tripByYearAndCountTrip/{year}/{count}")
    List<TripDTO> tripByYearAndCountTrip(@PathVariable int year, @PathVariable int countTrip);
}


