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
    private String totalTiempo;


    public MaintenanceReportDTO(double km_recorrido, double tiempo_uso, long id_scooter, int hours, int minutes) {
        this.km_recorrido = km_recorrido;
        this.tiempo_uso = tiempo_uso;
        this.id_scooter = id_scooter;
        this.totalTiempo = "Pausa total: Horas = "+hours+" Y minutos = "+minutes;
    }
}
