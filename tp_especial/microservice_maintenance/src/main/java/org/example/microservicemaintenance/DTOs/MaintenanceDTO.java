package org.example.microservicemaintenance.DTOs;

import lombok.Data;

@Data
public class MaintenanceDTO {
    private long id_maintenance;
    private long id_skateboard;
    private boolean repair;

    public MaintenanceDTO(){}

    public MaintenanceDTO(long id_maintenance, long id_skateBoard, boolean repair) {
        this.id_maintenance = id_maintenance;
        this.id_skateboard = id_skateBoard;
        this.repair = repair;
    }

}
