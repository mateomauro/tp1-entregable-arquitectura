package org.example.microservice_parking.Utils;

import org.example.microservice_parking.dtos.ParkingDto;
import org.example.microservice_parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {
    @Autowired
    private ParkingService parkingService;

    // dar de alta scooters
    public void loadParkings() throws Exception {
        ParkingDto parking1 = new ParkingDto(100.0, 100.0);
        ParkingDto parking2 = new ParkingDto(200.0, 200.0);
        try {
            if(parkingService.findAll().isEmpty()) {
                parkingService.save(parking1);
                parkingService.save(parking2);
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
