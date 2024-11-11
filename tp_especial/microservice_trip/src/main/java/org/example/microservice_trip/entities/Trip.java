package org.example.microservice_trip.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id_trip")
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_trip;
    private long id_scooter;
    private long id_account;
    private Date start_date;
    @Column(nullable = true)
    private Date end_date;
    private double km_traveled;
    private long id_start_parking;
    @Column(nullable = true)
    private long id_end_parking;

    public Trip(long id_scooter, long id_account, Date end_date, Date start_date, int km_traveled, long id_start_parking, long id_end_parking) {
        this.id_scooter = id_scooter;
        this.id_account = id_account;
        this.end_date = end_date;
        this.start_date = start_date;
        this.km_traveled = km_traveled;
        this.id_start_parking = id_start_parking;
        this.id_end_parking = id_end_parking;
    }

    public Trip(long id_trip,long id_account, long id_scooter, Date start_date, Date end_date, int km_traveled, long id_start_parking, long id_end_parking){
        this.id_scooter = id_scooter;
        this.id_account = id_account;
        this.end_date = end_date;
        this.start_date = start_date;
        this.km_traveled = km_traveled;
        this.id_trip = id_trip;
        this.id_start_parking = id_start_parking;
        this.id_end_parking = id_end_parking;
    }

    public Trip() {

    }

}
