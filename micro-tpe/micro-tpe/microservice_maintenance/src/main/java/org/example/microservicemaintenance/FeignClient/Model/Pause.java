package org.example.microservicemaintenance.FeignClient.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pause implements Serializable {

    private long id_scooter;
    private int hours;
    private int minutes;
}