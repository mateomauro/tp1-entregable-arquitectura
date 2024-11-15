package org.example.microservice_trip.feignClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScooterDto implements Serializable {
    private long id_scooter;
    private Double latitude;
    private Double longitude;
    private String QR_Code;
    private boolean available;
    private boolean in_maintenance;
    private Double usage_time;
    private Double km_traveled;

    @Override
    public String toString() {
        return "Scooter{" +
                "latitude=" + latitude +
                ", length=" + longitude +
                ", QR_Code='" + QR_Code + '\'' +
                ", available=" + available +
                ", usage_time=" + usage_time +
                ", km_traveled=" + km_traveled +
                ", id_scooter=" + id_scooter +
                '}';
    }
}