package org.microservice_user.feignClients.model;

import lombok.Data;

import java.util.Date;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class TripDTO {
    // Atributos de Trip
    private Long id_trip;
    private Long id_scooter;
    private Long id_user;
    private Date start_date;
    private Date end_date;
    private Double km_traveled;

    public TripDTO(Long id_trip, Long id_user, Date start_date, Long id_scooter, Date end_date, Double km_traveled) {
        this.id_trip = id_trip;
        this.id_user = id_user;
        this.start_date = start_date;
        this.id_scooter = id_scooter;
        this.end_date = end_date;
        this.km_traveled = km_traveled;
    }
}
