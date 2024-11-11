package org.example.microservicemaintenance.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceReportDTO {

    private double km_recorrido;
    private double tiempo_uso;
    private long id_scooter;
    private int hours;
    private int minutes;

}
