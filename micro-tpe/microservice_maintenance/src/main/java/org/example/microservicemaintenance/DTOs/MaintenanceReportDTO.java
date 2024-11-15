package org.example.microservicemaintenance.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceReportDTO {

    private double km_traveled;
    private double time_all;
    private long id_scooter;
    private String totalTiempo;

    //CONSTRUCTOR CON PAUSAS INCLUIDAS
    public MaintenanceReportDTO(double kmTraveled, double timeAll, long id_scooter, int hours, int minutes) {
        this.km_traveled = kmTraveled;
        this.time_all = timeAll;
        this.id_scooter = id_scooter;
        this.totalTiempo = "Pausa total: Horas = "+hours+" Y minutos = "+minutes;
    }

    //CONSTRUCTOR SIN PAUSAS
    public MaintenanceReportDTO(Double kmTraveled, Double timeAll, long idScooter) {
        this.km_traveled = kmTraveled;
        this.time_all = timeAll;
        this.id_scooter = idScooter;
    }

}