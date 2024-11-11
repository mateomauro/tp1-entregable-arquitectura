package org.example.microservice_trip.repository;

import org.example.microservice_trip.dtos.PauseDto;
import org.example.microservice_trip.entities.Pause;
import org.example.microservice_trip.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PauseRepository extends JpaRepository<Pause, Long> {

    @Modifying
    @Query("UPDATE Pause p SET p.end_date = :end_date WHERE p.trip.id_trip = :id_trip AND p.end_date IS NULL")
    void unpauseTrip(@Param("id_trip") long id_trip, @Param("end_date") Date end_date);


    @Query("SELECT new Pause(p.id_pause, p.start_date, p.end_date, p.trip) FROM Pause p WHERE p.end_date = :end_date AND p.trip.id_trip = :id_trip")
    Pause getByDateAndByidTrip(@Param("id_trip") long id_trip,@Param("end_date") Date end_date);

    @Query("SELECT p FROM Pause p WHERE p.trip.id_trip = :id_trip AND p.end_date IS NULL")
    List<Pause> findByTripIdAndEndDateNull(@Param("id_trip") long id_trip);

    @Query("SELECT p FROM Pause p JOIN p.trip")
    List<Pause> getAllPauseByScooter();

    @Query("SELECT new Pause(p.start_date,p.end_date,t) FROM Pause p JOIN p.trip t WHERE t.id_trip = :id_trip")
    List<Pause> getAllPauseByIdTrip(@Param("id_trip") long id_trip);
}


