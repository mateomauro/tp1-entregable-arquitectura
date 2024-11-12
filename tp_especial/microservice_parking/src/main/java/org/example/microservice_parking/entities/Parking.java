package org.example.microservice_parking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_parking;
    private double latitude;
    private double longitude;

    public Parking (double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Parking() {

    }
}
