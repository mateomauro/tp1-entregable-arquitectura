package org.example.microservicemaintenance.DTOs;

import lombok.Data;

@Data
public class MaintenanceScooterDTO {
    private long id_scooter;
    private boolean repair;

    public MaintenanceScooterDTO(){}

    //Contructor para admin
    public MaintenanceScooterDTO(long id_scooter, boolean repair){
        this.id_scooter = id_scooter;
        this.repair = repair;
    }
}
