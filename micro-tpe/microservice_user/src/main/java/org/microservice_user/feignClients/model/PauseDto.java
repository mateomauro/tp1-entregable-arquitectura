package org.microservice_user.feignClients.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class
PauseDto {
    private Long id_trip;
    private Date start_date;
    private Date end_date;
}
