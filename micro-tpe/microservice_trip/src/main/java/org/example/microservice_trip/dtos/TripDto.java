package org.example.microservice_trip.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id_trip;
    private Long id_scooter;
    private Long id_user;
    private Long id_account;
    private Date start_date;
    private Date end_date;
    private Double km_traveled;

    public TripDto(Long id_trip, Long id_user, Long id_account, Date start_date, Long id_scooter, Date end_date, Double km_traveled) {
        this.id_trip = id_trip;
        this.id_user = id_user;
        this.id_account = id_account;
        this.start_date = start_date;
        this.id_scooter = id_scooter;
        this.end_date = end_date;
        this.km_traveled = km_traveled;
    }
}
