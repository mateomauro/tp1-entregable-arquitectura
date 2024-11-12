package org.example.microservice_trip.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.microservice_trip.dtos.*;
import org.example.microservice_trip.entities.Pause;
import org.example.microservice_trip.entities.Trip;
import org.example.microservice_trip.feignClient.ParkingFeignClient;
import org.example.microservice_trip.feignClient.ScooterFeignClient;
import org.example.microservice_trip.feignClient.model.ScooterDto;
import org.example.microservice_trip.repository.PauseRepository;
import org.example.microservice_trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Service
public class TripService{
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private PauseRepository pauseRepository;
    @Autowired
    private ScooterFeignClient scooterFeignClient;
    @Autowired
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
    // TENDRÍA Q DEVOLVER TRIP DTO
    public TripDto save(long id_account, long id_scooter) throws Exception {
        try {
            //traigo el scooter para saber donde incia el viaje
            ScooterDto scooterDto = scooterFeignClient.getScooterById(id_scooter);
            //traigo la parada donde el scooter comienza el viaje
            ParkingDto parkingDto = parkingFeignClient.getParkingByLatitudeAndLongitude(scooterDto.getLatitude(),scooterDto.getLongitude());
            // la fecha del viaje la setiamos en null y la parada destino en -1
            Trip nuevoTrip = new Trip(id_scooter,id_account,null, new Date(),0,parkingDto.getId_parking(), -1);
            tripRepository.save(nuevoTrip);
            return new TripDto(nuevoTrip.getId_account(), nuevoTrip.getStart_date(),nuevoTrip.getId_scooter(),nuevoTrip.getEnd_date(), nuevoTrip.getKm_traveled());
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //END A TRIP
    @Transactional
    public TripDto endTrip(long id_user) throws Exception{
        try {
            //traemos el scooter que esta usando
            Long id_scooter = tripRepository.findIdScooter(id_user);
            System.out.println("scooter es :" + id_scooter);
            //traemos el scooter para saber en que latitud y longitud esta
            ScooterDto scooterDto = scooterFeignClient.getScooterById(id_scooter);
            //traemos la parada en la que el scooter esta
            ParkingDto parkingDtoEnd = parkingFeignClient.getParkingByLatitudeAndLongitude(scooterDto.getLatitude(),scooterDto.getLongitude());

            //si el scooter esta en una parada "scooterCan = true"
            boolean scooterCan = parkingDtoEnd != null;

            System.out.println(scooterCan);
            //si es false , hay una exception
            if (!scooterCan){
                throw new Exception("error");
            }

            //traemos el viaje completo
            Trip trip = tripRepository.getByUserAndByScooter(id_user,id_scooter);
            System.out.println("viaje: " + trip);
            //traemos el parking donde comenzo el scooter
            ParkingDto parkingDtoStart = parkingFeignClient.getParkingById(trip.getId_start_parking());
            System.out.println("parking inicio: " + parkingDtoStart);
            //calculamos la distancia que hizo el scooter
            double kmTraveled = this.calculateDistance(parkingDtoStart.getLatitud(),parkingDtoStart.getLongitud(),parkingDtoEnd.getLatitud(),parkingDtoEnd.getLongitud());
            System.out.println("distancia: " + kmTraveled);
            Date rigthNow = new Date();
            //finalizamos el trip con la fecha actual y los km realizados
            tripRepository.endTrip(trip.getId_trip(), rigthNow, parkingDtoEnd.getId_parking(), kmTraveled);
            System.out.println("finalizo el viaje");
            trip = tripRepository.findById(trip.getId_trip());
            trip.setEnd_date(rigthNow);
            trip.setId_end_parking(parkingDtoEnd.getId_parking());
            trip.setKm_traveled(kmTraveled);
            return new TripDto(id_user, trip.getStart_date(), scooterDto.getId_scooter(), trip.getEnd_date(), trip.getKm_traveled());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // PAUSE A TRIP
    public Pause pauseTrip(long id_user) throws Exception {
        try {
            Long id_scooter = tripRepository.findIdScooter(id_user);
            Trip trip = tripRepository.getByUserAndByScooter(id_user, id_scooter);
            return pauseRepository.save(new Pause(new Date(), null, trip));
        } catch (Exception e) {
            throw new Exception("Error while pausing trip: " + e.getMessage());
        }
    }

    // UNPAUSE A TRIP
    @Transactional
    public Pause unpauseTrip(long id_user) throws Exception {
        try {
            Long id_scooter = tripRepository.findIdScooter(id_user);
            Trip trip = tripRepository.getByUserAndByScooter(id_user, id_scooter);
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

