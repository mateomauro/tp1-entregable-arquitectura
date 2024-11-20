package com.tpe.micro.scooters;


import com.tpe.micro.scooters.Entities.Scooter;
import com.tpe.micro.scooters.Services.Exceptions.ScooterNotFoundException;
import com.tpe.micro.scooters.Services.ScooterService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTests {

	@Autowired
	private ScooterService scooterService;

	@Test
	public void TEST_scooterDelete() throws Exception {
		Assert.assertThrows(ScooterNotFoundException.class, () -> {scooterService.delete(-10L);});
	}
}
