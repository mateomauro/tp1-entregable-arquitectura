package org.example.microservice_trip.dtos;

import jakarta.persistence.Id;
import org.example.microservice_trip.entities.Trip;

import java.sql.Time;
import java.util.Date;

public class TripDto {
    private long id_scooter;
    private long id_account;
    private Date start_date;
    private Date end_date;
    private double km_traveled;

    public TripDto(long id_account, Date start_date, long id_scooter, Date end_date, double km_traveled) {
        this.id_account = id_account;
        this.start_date = start_date;
        this.id_scooter = id_scooter;
        this.end_date = end_date;
        this.km_traveled = km_traveled;
    }

    public long getId_scooter() {
        return id_scooter;
    }

    public long getId_account() {
        return id_account;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public double getKm_traveled() {
        return km_traveled;
    }
}
