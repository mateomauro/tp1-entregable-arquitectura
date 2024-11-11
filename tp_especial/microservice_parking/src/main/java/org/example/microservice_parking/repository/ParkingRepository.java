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
    @Query("UPDATE Parking SET latitud = :latitud, longitud = :longitud WHERE id_parking = :id_parking")
    void update(@Param("id_parking") long id_parking,@Param("latitud") double latitud, @Param("longitud") double longitud);

    @Query("SELECT p FROM Parking p WHERE p.latitud = :latitud AND p.longitud = :longitud")
    Parking getParkingByLatitudAndLongitud(@Param("latitud") double latitud, @Param("longitud") double longitud);
}
