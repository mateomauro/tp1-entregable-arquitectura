package org.example.microservice_trip;

import org.example.microservice_trip.dtos.TripDto;
import org.example.microservice_trip.service.TripService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MicroserviceTripApplicationTests {

	@Autowired
	private TripService tripService;

	@Test
	public void test_tripExistsOne() throws Exception {
		TripDto test = tripService.getTripByIdTrip(-1L);
		Assert.assertNull(test);
	}

}
