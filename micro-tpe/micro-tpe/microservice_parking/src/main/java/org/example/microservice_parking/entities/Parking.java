package org.example.microservice_parking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_parking;
    private Double latitude;
    private Double longitude;

    public Parking (Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
