package org.example.microservice_trip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Entity
@Data
public class Pause implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pause;
    private Date start_date;
    @Column(nullable = true)
    private Date end_date;

    @ManyToOne
    private Trip trip;

    public Pause (Date start_date, Date end_date, Trip trip){
        this.start_date = start_date;
        this.end_date = end_date;
        this.trip = trip;
    }

    public Pause (long id_pause,Date start_date, Date end_date, Trip trip){
        this.start_date = start_date;
        this.end_date = end_date;
        this.trip = trip;
        this.id_pause = id_pause;
    }

    public Pause() {

    }
}
