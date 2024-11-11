package com.tpe.micro.scooters.Repositories;

import com.tpe.micro.scooters.Entities.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {


    @Query("SELECT s FROM Scooter s GROUP BY s.id_scooter ORDER BY s.km_traveled DESC")
    List<Scooter> findByKm();

    @Query("SELECT s FROM Scooter s WHERE s.length BETWEEN :minLongitude AND :maxLongitude AND s.latitude BETWEEN :minLatitude AND :maxLatitude AND s.available = true")
    List<Scooter> findNearby(Double minLongitude, Double maxLongitude, Double minLatitude, Double maxLatitude);

    @Query("SELECT s FROM Scooter s WHERE s.available = true ORDER BY (POWER(s.latitude - :latitude, 2) + POWER(s.length - :length, 2)) ASC LIMIT 1")
    Scooter findAScooterNear(Double latitude, Double length);

    @Query("SELECT s FROM Scooter s WHERE s.in_maintenance = false")
    List<Scooter> getActives();
}
