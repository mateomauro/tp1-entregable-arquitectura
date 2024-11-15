package org.example.microservicemaintenance.DTOs;

import lombok.Data;

@Data
public class MaintenanceDTO {
    private long id_maintenance;
    private long id_scooter;
    private boolean repair;

    public MaintenanceDTO(){}

    public MaintenanceDTO(long id_maintenance, long id_scooter, boolean repair) {
        this.id_maintenance = id_maintenance;
        this.id_scooter = id_scooter;
        this.repair = repair;
    }

}
