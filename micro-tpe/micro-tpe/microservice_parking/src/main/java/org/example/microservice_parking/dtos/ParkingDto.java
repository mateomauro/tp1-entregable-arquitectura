package org.example.microservice_parking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Long id_parking;
    private Double latitude;
    private Double longitude;

    public ParkingDto(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
