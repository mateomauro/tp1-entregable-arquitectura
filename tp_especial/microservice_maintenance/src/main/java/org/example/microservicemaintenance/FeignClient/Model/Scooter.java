package org.example.microservicemaintenance.FeignClient.Model;

//package com.tpe.micro.skateboard.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scooter implements Serializable {

    private long id_scooter;
    private Double latitude;
    private Double length;
    private String QR_Code;
    private boolean available;
    private boolean in_maintenance;
    private Double usage_time;
    private Double km_traveled;

}
