package org.example.microservice_parking.repository;

import org.example.microservice_parking.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    @Modifying
    @Query("UPDATE Parking p SET p.latitude = :latitude, p.longitude = :longitude WHERE p.id_parking = :id_parking")
    void update(@Param("id_parking") long id_parking, @Param("latitude") double latitud, @Param("longitude") double longitude);

    @Query("SELECT p FROM Parking p WHERE p.latitude = :latitude AND p.longitude = :longitude")
    Parking getParkingByLatitudeAndLongitude(@Param("latitude") double latitude, @Param("longitude") double longitude);
}
