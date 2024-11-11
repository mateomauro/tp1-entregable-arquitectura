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
    private double latitud;
    private double longitud;

    public Parking (double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Parking() {

    }
}
