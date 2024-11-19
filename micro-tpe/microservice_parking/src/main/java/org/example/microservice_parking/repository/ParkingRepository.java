package org.example.microservice_parking.repository;

import org.example.microservice_parking.entities.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends MongoRepository<Parking, Long> {

    // Obtener parking por latitud y longitud
    @Query("{ 'latitude': ?0, 'longitude': ?1 }")
    Parking getParkingByLatitudeAndLongitude(Double latitude, Double longitude);
}
