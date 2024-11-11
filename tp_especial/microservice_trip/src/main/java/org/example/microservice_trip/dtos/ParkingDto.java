package org.example.microservice_trip.dtos;

import lombok.Data;

@Data
public class ParkingDto {
    private long id_parking;
    private double latitud;
    private double longitud;

    public ParkingDto(long id_parking,double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
        this.id_parking = id_parking;
    }
}

