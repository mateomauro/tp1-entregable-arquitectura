package org.example.microservice_trip.repository;

import jakarta.transaction.Transactional;
import org.example.microservice_trip.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT t.id_scooter FROM Trip t WHERE t.id_account = :id_user")
    Long findIdScooter(@Param("id_user") Long id_user);

    //modify a trip
    @Modifying
    @Transactional
    @Query(value = "UPDATE Trip SET id_scooter = :id_scooter, id_account = :id_account, start_date = :start_date, end_date = :end_date, km_traveled = :km_traveled WHERE id_trip = :id_trip")
    void update(@Param("id_trip") long id_trip, @Param("id_scooter") long id_scooter, @Param("id_account") long id_account, @Param("start_date") Date start_date, @Param("end_date") Date end_date, @Param("km_traveled") double km_traveled);

    //end a trip
    @Modifying
    @Transactional
    @Query(value = "UPDATE Trip SET end_date = :end_date, id_end_parking = :id_end_parking, km_traveled = :km_traveled WHERE id_trip = :id_trip")
    void endTrip(@Param("id_trip") long id_trip, @Param("end_date") Date end_date, @Param("id_end_parking") long id_end_parking, @Param("km_traveled") double km_traveled);

    //bring the trip by id_account and id_scooter and end_date == null
    @Query("SELECT t FROM Trip t WHERE t.id_account = :id_account AND t.id_scooter = :id_scooter AND t.end_date IS NULL")
    Trip getByUserAndByScooter(@Param("id_account") long id_account, @Param("id_scooter") long id_scooter);

    //find by id
    @Query("SELECT t FROM Trip t WHERE t.id_trip = :id_trip")
    Trip findById(@Param("id_trip") long id_trip);

    //
    @Query("SELECT t FROM Trip t WHERE YEAR(t.end_date) = :year GROUP BY YEAR(t.end_date) HAVING COUNT(t) > :count")
    List<Trip> getTripByYearAndCountTrip(@Param("year") int year, @Param("count") long count);

}
