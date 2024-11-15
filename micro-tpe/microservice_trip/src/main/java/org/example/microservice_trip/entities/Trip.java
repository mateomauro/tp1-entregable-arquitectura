package org.example.microservice_trip.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_trip")
@NoArgsConstructor
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_trip;
    private Long id_scooter;
    private Long id_user;
    private Long id_account;
    private Date start_date;
    @Column(nullable = true)
    private Date end_date;
    private Double km_traveled;
    private Long id_start_parking;
    @Column(nullable = true)
    private Long id_end_parking;

    public Trip(Long id_scooter, Long id_user, Long id_account, Date end_date, Date start_date, Double km_traveled, Long id_start_parking, Long id_end_parking) {
        this.id_scooter = id_scooter;
        this.id_user = id_user;
        this.id_account = id_account;
        this.end_date = end_date;
        this.start_date = start_date;
        this.km_traveled = km_traveled;
        this.id_start_parking = id_start_parking;
        this.id_end_parking = id_end_parking;
    }

    public Trip(Long id_trip, Long id_user, Long id_account, Long id_scooter, Date start_date, Date end_date, Double km_traveled, Long id_start_parking, Long id_end_parking){
        this.id_scooter = id_scooter;
        this.id_user = id_user;
        this.id_account = id_account;
        this.end_date = end_date;
        this.start_date = start_date;
        this.km_traveled = km_traveled;
        this.id_trip = id_trip;
        this.id_start_parking = id_start_parking;
        this.id_end_parking = id_end_parking;
    }

}
