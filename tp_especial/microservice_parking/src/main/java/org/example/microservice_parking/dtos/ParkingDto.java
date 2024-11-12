package org.example.microservice_parking.dtos;

import lombok.Data;

@Data
public class ParkingDto {
    private long id_parking;
    private double latitude;
    private double longitude;

    public ParkingDto(long id_parking, double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_parking = id_parking;
    }
}
