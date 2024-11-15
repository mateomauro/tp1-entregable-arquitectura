package com.tpe.micro.scooters.Utils;

import com.tpe.micro.scooters.DTOs.ScooterRequestDTO;
import com.tpe.micro.scooters.Services.ScooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {
    @Autowired
    private ScooterService scooterService;

    // dar de alta scooters
    public void loadScooters() throws Exception {
        ScooterRequestDTO scooter1 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        ScooterRequestDTO scooter2 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        ScooterRequestDTO scooter3 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        ScooterRequestDTO scooter4 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        ScooterRequestDTO scooter5 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        ScooterRequestDTO scooter6 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        ScooterRequestDTO scooter7 = new ScooterRequestDTO(100.0, 100.0, "2321as65d432asd");
        try {
            if(scooterService.getAll().isEmpty()) {
                scooterService.insert(scooter1);
                scooterService.insert(scooter2);
                scooterService.insert(scooter3);
                scooterService.insert(scooter4);
                scooterService.insert(scooter5);
                scooterService.insert(scooter6);
                scooterService.insert(scooter7);
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
