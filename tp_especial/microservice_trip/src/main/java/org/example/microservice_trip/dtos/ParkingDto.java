package org.example.microservice_trip.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private long id_parking;
    private double latitud;
    private double longitud;
}

