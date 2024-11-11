package org.example.microservice_trip.dtos;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.microservice_trip.entities.Trip;

import java.sql.Time;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
