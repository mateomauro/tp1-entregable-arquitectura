package org.example.microservice_trip.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.microservice_trip.dtos.*;
import org.example.microservice_trip.entities.Pause;
import org.example.microservice_trip.entities.Trip;
import org.example.microservice_trip.feignClient.ParkingFeignClient;
import org.example.microservice_trip.feignClient.ScooterFeignClient;
import org.example.microservice_trip.repository.PauseRepository;
import org.example.microservice_trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.*;

@Service
public class TripService{
    private TripRepository tripRepository;
    private PauseRepository pauseRepository;
    private ScooterFeignClient scooterFeignClient;
    private ParkingFeignClient parkingFeignClient;


    //get all trips
    public List<Trip> findAll() throws Exception{
        try{
            return this.tripRepository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //register a trip
   //public Trip save(Trip trip) throws Exception {
   //    try{
   //        return tripRepository.save(trip);
   //    }catch (Exception e){
   //        throw new Exception(e.getMessage());
   //    }
   //}

    // delete a trip
    public void delete(long id_trip) throws Exception {
        try {
            this.tripRepository.deleteById(id_trip);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //modify a trip
    public Trip update(long id_trip, Trip trip) throws Exception {
        try {
            tripRepository.update(id_trip,trip.getId_scooter(),trip.getId_account(),trip.getStart_date(),trip.getEnd_date(),trip.getKm_traveled());
            return trip;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //START A TRIP
    public Trip save(long id_account, long id_scooter) throws Exception {
        try{
            ScooterDto scooterDto = scooterFeignClient.getScooterById(id_scooter);
            ParkingDto parkingDto = parkingFeignClient.getParkingByLatitudAndLongitud(scooterDto.getLatitude(),scooterDto.getLength());
            // Sets end_date to null and start_date to the current date
            Trip nuevoTrip = new Trip(id_scooter,id_account,null, new Date(),0,parkingDto.getId_parking(), -1);
            return tripRepository.save(nuevoTrip);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //END A TRIP
    @Transactional
    public Trip endTrip(long id_account, long id_scooter) throws Exception{
        try {
            ScooterDto scooterDto = scooterFeignClient.getScooterById(id_scooter);
            ParkingDto parkingDtoEnd = parkingFeignClient.getParkingByLatitudAndLongitud(scooterDto.getLatitude(),scooterDto.getLength());
            boolean scooterCan = parkingDtoEnd != null;

            if (!scooterCan){
                throw new Exception("error");
            }

            Trip trip = tripRepository.getByAccountAndByScooter(id_account,id_scooter);
            ParkingDto parkingDtoStart = parkingFeignClient.getParkingById(trip.getId_start_parking());
            double kmTraveled = this.calculateDistance(parkingDtoStart.getLatitud(),parkingDtoStart.getLongitud(),parkingDtoEnd.getLatitud(),parkingDtoEnd.getLongitud());

            Date rigthNow = new Date();
            tripRepository.endTrip(trip.getId_trip(), rigthNow, parkingDtoEnd.getId_parking(), kmTraveled);

            trip = tripRepository.findById(trip.getId_trip());
            trip.setEnd_date(rigthNow);
            trip.setId_end_parking(parkingDtoEnd.getId_parking());
            trip.setKm_traveled(kmTraveled);
            return trip;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // PAUSE A TRIP
    public Pause pauseTrip(long id_account, long id_scooter) throws Exception {
        try {
            Trip trip = tripRepository.getByAccountAndByScooter(id_account,id_scooter);
            return pauseRepository.save(new Pause(new Date(), null, trip));
        } catch (Exception e) {
            throw new Exception("Error while pausing trip: " + e.getMessage());
        }
    }

    // UNPAUSE A TRIP
    @Transactional
    public Pause unpauseTrip(long id_account, long id_scooter) throws Exception {
        try {
            Trip trip = tripRepository.getByAccountAndByScooter(id_account, id_scooter);
            if (trip == null) {
                throw new EntityNotFoundException("No se encontró el viaje con esos parámetros.");
            }

            List<Pause> pauses = pauseRepository.findByTripIdAndEndDateNull(trip.getId_trip());
            if (pauses.isEmpty()) {
                throw new Exception("No pause found for the trip.");
            }
            Date rightNow = new Date();
            pauseRepository.unpauseTrip(trip.getId_trip(), rightNow);
            return pauseRepository.getByDateAndByidTrip(trip.getId_trip(), rightNow);
        } catch (Exception e) {
            throw new Exception("Error while pausing trip: " + e.getMessage());
        }
    }

    //GET ALL PAUSE BY SCOOTER
    public List<PauseOfScooterDto> getAllPauseByScooter() throws Exception {
        try {
            List<Pause> pauses = this.pauseRepository.getAllPauseByScooter();
            Map<Long, Duration> durationByScooter = new HashMap<>();

            for (Pause pause : pauses) {
                long idScooter = pause.getTrip().getId_scooter();

                Instant startInstant = pause.getStart_date().toInstant();
                Instant endInstant = pause.getEnd_date().toInstant();

                Duration pauseDuration = Duration.between(startInstant, endInstant);

                durationByScooter.put(idScooter,
                        durationByScooter.getOrDefault(idScooter, Duration.ZERO).plus(pauseDuration));
            }

            List<PauseOfScooterDto> result = new ArrayList<>();
            for (Map.Entry<Long, Duration> entry : durationByScooter.entrySet()) {
                long idScooter = entry.getKey();
                Duration totalDuration = entry.getValue();

                int hours = (int) totalDuration.toHours();
                int minutes = totalDuration.toMinutesPart();

                result.add(new PauseOfScooterDto(idScooter, hours, minutes));
            }

            return result;
        } catch (Exception e) {
            throw new Exception("Error al obtener las pausas por scooter: " + e.getMessage());
        }
    }

    public List<PauseDto> getAllPauseByIdTrip(long id_pause) throws Exception{
        try{
            List<Pause> pauses = this.pauseRepository.getAllPauseByIdTrip(id_pause);
            List<PauseDto> pausesDto = new ArrayList<>();
            for(Pause pause: pauses) {
                pausesDto.add(new PauseDto(pause.getTrip().getId_trip(), pause.getStart_date(),pause.getEnd_date()));
            }
            return pausesDto;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public TripDto getTripByIdTrip(long id_trip) throws Exception{
        try{
            Trip trip = tripRepository.findById(id_trip);
            return new TripDto(trip.getId_account(),trip.getStart_date(),trip.getId_scooter(),trip.getEnd_date(),trip.getKm_traveled());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<TripDto> getTripByYearAndCountTrip(int year, int count ) throws Exception {
        try{
            List<Trip> trips = tripRepository.getTripByYearAndCountTrip(year,count);
            List<TripDto> tripDtos = new ArrayList<>();
            for(Trip trip: trips){
                tripDtos.add(new TripDto(trip.getId_account(),trip.getStart_date(),trip.getId_scooter(),trip.getEnd_date(),trip.getKm_traveled()));
            }
            return tripDtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    private double calculateDistance(double startLat, double startLon, double endLat, double endLon) {
         double EARTH_RADIUS = 6371.0;

        double startLatRad = Math.toRadians(startLat);
        double startLonRad = Math.toRadians(startLon);
        double endLatRad = Math.toRadians(endLat);
        double endLonRad = Math.toRadians(endLon);

        double deltaLat = endLatRad - startLatRad;
        double deltaLon = endLonRad - startLonRad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(startLatRad) * Math.cos(endLatRad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}

