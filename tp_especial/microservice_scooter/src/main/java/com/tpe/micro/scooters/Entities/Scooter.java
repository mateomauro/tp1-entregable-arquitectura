package com.tpe.micro.scooters.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Scooter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_scooter;
    private Double latitude;
    private Double longitude;
    private String QR_Code;
    private boolean available;
    private boolean in_maintenance;
    private Double usage_time;
    private Double km_traveled;

    public Scooter(double latitude, double longitude, String QR_Code) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.QR_Code = QR_Code;
        this.available = true;
        this.in_maintenance = false;
        this.usage_time = 0.0;
        this.km_traveled = 0.0;
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", QR_Code='" + QR_Code + '\'' +
                ", available=" + available +
                ", usage_time=" + usage_time +
                ", km_traveled=" + km_traveled +
                ", id_scooter=" + id_scooter +
                '}';
    }
}


