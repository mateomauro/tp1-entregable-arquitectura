package org.example.microservice_parking;

import org.example.microservice_parking.dtos.ParkingDto;
import org.example.microservice_parking.service.ParkingService;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class MicroserviceParkingApplicationTests {

    @Autowired
    private ParkingService parkingService;

    private ParkingDto parkingDto;

    @Test
    public void test_update() throws Exception {
        parkingDto = parkingService.getParkingById(2L);

        ParkingDto parking = new ParkingDto(1000.0,1000.0);

        ParkingDto test = parkingService.update(2L, parking);

        Assert.isTrue(test.getLongitude()==parking.getLongitude() && test.getLatitude()==parking.getLatitude(), "Anda mal");
    }

    @After
    public void tearDown() throws Exception {
        this.parkingService.update(2L, parkingDto);
    }

}
