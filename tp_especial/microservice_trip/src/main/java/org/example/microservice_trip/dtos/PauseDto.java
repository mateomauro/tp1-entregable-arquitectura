package org.example.microservice_trip.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class PauseDto {
    private long id_trip;
    private Date start_date;
    private Date end_date;

    public PauseDto (long id_trip, Date start_date, Date end_date){
        this.id_trip = id_trip;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
