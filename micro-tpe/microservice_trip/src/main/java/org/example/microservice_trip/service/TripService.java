package org.example.microservice_trip.service;

import jakarta.transaction.Transactional;
import org.example.microservice_trip.dtos.*;
import org.example.microservice_trip.entities.Pause;
import org.example.microservice_trip.entities.Trip;
import org.example.microservice_trip.feignClient.*;
import org.example.microservice_trip.feignClient.model.AccountRequestDTO;
import org.example.microservice_trip.feignClient.model.AccountResponseDTO;
import org.example.microservice_trip.feignClient.model.BillingDTO;
import org.example.microservice_trip.feignClient.model.ScooterDto;
import org.example.microservice_trip.repository.PauseRepository;
import org.example.microservice_trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private PauseRepository pauseRepository;
    @Autowired
    private ScooterFeignClient scooterFeignClient;
    @Autowired
    private ParkingFeignClient parkingFeignClient;
    @Autowired
    private AdminsFeignClient adminsFeignClient;
    @Autowired
    private AccountFeignClient accountFeignClient;


    //get all trips
    public List<Trip> findAll() throws Exception {
        try {
            return this.tripRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // delete a trip
    public void delete(Long id_trip) throws Exception {
        try {
            this.tripRepository.deleteById(id_trip);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //modify a trip
    public Trip update(Long id_trip, Trip trip) throws Exception {
        try {
            tripRepository.update(id_trip, trip.getId_scooter(), trip.getId_account(), trip.getStart_date(), trip.getEnd_date(), trip.getKm_traveled());
            return trip;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //START A TRIP
    public TripDto save(Long id_user, Long id_account, Long id_scooter) throws Exception {
        try {
            // Verificamos si ya existe un viaje en curso para el usuario y el scooter
            Trip existingTrip = tripRepository.getByUserAndByScooter(id_user, id_account, id_scooter);
            if (existingTrip != null) {
                throw new Exception("El usuario ya tiene un viaje activo con este monopatín. Debe finalizar el viaje actual antes de iniciar uno nuevo.");
            }

            // Traemos el scooter para saber dónde inicia el viaje
            ScooterDto scooterDto = scooterFeignClient.getScooterById(id_scooter);

            // Traemos la parada donde el scooter comienza el viaje
            ParkingDto parkingDto = parkingFeignClient.getParkingByLatitudeAndLongitude(scooterDto.getLatitude(), scooterDto.getLongitude());

            // el nuevo viaje con fecha de finalización en null y parada destino en null
            Trip nuevoTrip = new Trip(id_scooter, id_user, id_account, null, new Date(), 0.0, parkingDto.getId_parking(), null);
            tripRepository.save(nuevoTrip);

            // Retornar DTO del viaje
            return new TripDto(nuevoTrip.getId_trip(), nuevoTrip.getId_user(), nuevoTrip.getId_account(), nuevoTrip.getStart_date(), nuevoTrip.getId_scooter(), nuevoTrip.getEnd_date(), nuevoTrip.getKm_traveled());
        } catch (Exception e) {
            throw new Exception("Error al iniciar el viaje: " + e.getMessage());
        }
    }


    @Transactional
    public TripDto endTrip(Long id_user, Long id_account) throws Exception {
        try {
            // scooter que el usuario está utilizando actualmente
            Long id_scooter = tripRepository.findIdScooter(id_account);
            if (id_scooter == null) {
                throw new Exception("No se encontró un viaje en curso para el usuario.");
            }

            // datos del scooter (ubicación actual)
            ScooterDto scooterDto = scooterFeignClient.getScooterById(id_scooter);
            if (scooterDto == null) {
                throw new Exception("Scooter no encontrado.");
            }

            // la parada actual basada en la ubicación del scooter
            ParkingDto parkingDtoEnd = parkingFeignClient.getParkingByLatitudeAndLongitude(scooterDto.getLatitude(), scooterDto.getLongitude());
            if (parkingDtoEnd == null) {
                throw new Exception("El scooter no está en una parada válida.");
            }

            // el viaje activo del usuario y scooter
            Trip trip = tripRepository.getByUserAndByScooter(id_user, id_account, id_scooter);
            if (trip == null || trip.getEnd_date() != null) {
                throw new Exception("No se encontró un viaje activo para finalizar.");
            }

            // la ubicación de inicio del viaje
            ParkingDto parkingDtoStart = parkingFeignClient.getParkingById(trip.getId_start_parking());
            if (parkingDtoStart == null) {
                throw new Exception("No se encontró la parada de inicio.");
            }

            // Calculamos la distancia recorrida (en km)
            double kmTraveled = calculateDistance(parkingDtoStart.getLatitude(), parkingDtoStart.getLongitude(), parkingDtoEnd.getLatitude(), parkingDtoEnd.getLongitude());
            // Actualizamos el scooter con la distancia y tiempo de uso
            updateScooterMetrics(scooterDto, kmTraveled, trip);

            // Finalizamos el viaje en la base de datos
            Date endDate = new Date();
            tripRepository.endTrip(trip.getId_trip(), endDate, parkingDtoEnd.getId_parking(), kmTraveled);


            // Calculamos el costo del viaje
            BillingDTO billingDTO = adminsFeignClient.calculateCost(trip.getId_trip(), kmTraveled);

            // Cobramos el viaje en la cuenta del usuario
            accountFeignClient.discountBalance(id_account, billingDTO.getTotalTrip());


            return new TripDto(trip.getId_trip(), trip.getId_user(), id_account, trip.getStart_date(), scooterDto.getId_scooter(), endDate, kmTraveled);


        } catch (Exception e) {
            throw new Exception("Error al finalizar el viaje: " + e.getMessage());
        }
    }

    /**
     * Actualiza los kilómetros recorridos y el tiempo de uso del scooter.
     */
    private void updateScooterMetrics(ScooterDto scooterDto, Double kmTraveled, Trip trip) {
        // Suma los kilómetros recorridos
        double updatedKmTraveled = scooterDto.getKm_traveled() + kmTraveled;
        scooterDto.setKm_traveled(updatedKmTraveled);

        // Calculamos tiempo efectivo del viaje (sin pausas)
        double totalUsageTime = scooterDto.getUsage_time() != null ? scooterDto.getUsage_time() : 0;
        int totalPausedMinutes = calculateTotalPausedMinutes(trip);
        long tripDurationMinutes = calculateTripDurationInMinutes(trip.getStart_date(), new Date()) - totalPausedMinutes;

        // Sumamos el tiempo de uso al scooter
        totalUsageTime += tripDurationMinutes;
        scooterDto.setUsage_time(totalUsageTime);

        // Actualizamos scooter en microservicio de scooters
        scooterFeignClient.updateScooter(scooterDto.getId_scooter(), scooterDto);
    }

    /**
     * Calcula la duración total del viaje en minutos, excluyendo pausas.
     */
    private Long calculateTripDurationInMinutes(Date startDate, Date endDate) {
        return Duration.between(startDate.toInstant(), endDate.toInstant()).toMinutes();
    }

    /**
     * Calcula el tiempo total en minutos de todas las pausas asociadas a un viaje.
     */
    private int calculateTotalPausedMinutes(Trip trip) {
        List<Pause> pauses = pauseRepository.getAllPauseByIdTrip(trip.getId_trip());
        int totalPausedMinutes = 0;
        for (Pause pause : pauses) {
            if (pause.getEnd_date() != null)
              totalPausedMinutes += (int) Duration.between(pause.getStart_date().toInstant(), pause.getEnd_date().toInstant()).toMinutes();
        }
        return totalPausedMinutes;
    }


    // PAUSE A TRIP
    public PauseDto pauseTrip(Long id_user, Long id_account) throws Exception {
        try {
            // el scooter que el usuario está utilizando actualmente
            Long id_scooter = tripRepository.findIdScooter(id_account);
            if (id_scooter == null) {
                throw new Exception("No se encontró un viaje en curso para el usuario.");
            }

            // el viaje activo del usuario y scooter
            Trip trip = tripRepository.getByUserAndByScooter(id_user, id_account, id_scooter);
            if (trip == null || trip.getEnd_date() != null) {
                throw new Exception("No se encontró un viaje activo para pausar.");
            }

            // Verificamos que no haya una pausa activa (sin fecha de finalización)
            Pause activePause = pauseRepository.findActivePauseByTripId(trip.getId_trip());
            if (activePause != null) {
                throw new Exception("Ya existe una pausa activa para este viaje.");
            }

            // Creamos y guardamos una nueva pausa con la fecha de inicio actual
            Pause newPause = new Pause(new Date(), null, trip);
            pauseRepository.save(newPause);
            return new PauseDto(newPause.getId_pause(), newPause.getStart_date(), newPause.getEnd_date());

        } catch (Exception e) {
            throw new Exception("Error al pausar el viaje: " + e.getMessage());
        }
    }


    // UNPAUSE A TRIP
    @Transactional
    public PauseDto unpauseTrip(Long id_user, Long id_account) throws Exception {
        try {
            // Verificamos si el usuario tiene un scooter activo y obtener el ID
            Long id_scooter = tripRepository.findIdScooter(id_account);
            if (id_scooter == null) {
                throw new Exception("No scooter asociado a un usuario.");
            }

            // Obtenemos el viaje activo del usuario y validar su existencia
            Trip trip = tripRepository.getByUserAndByScooter(id_user, id_account, id_scooter);
            if (trip == null) {
                throw new Exception("no hay un viaje activo.");
            }

            // Obtener la pausa activa del viaje
            Pause activePause = pauseRepository.findActivePauseByTripId(trip.getId_trip());
            if (activePause == null) {
                throw new Exception("No hay una pausa activa.");
            }

            // Establecer la fecha de finalización de la pausa actual
            Date rightNow = new Date();
            activePause.setEnd_date(rightNow);

            // Guardar la pausa actualizada en el repositorio
            pauseRepository.save(activePause);
            return new PauseDto(activePause.getId_pause(), activePause.getStart_date(), activePause.getEnd_date());

        } catch (Exception e) {
            throw new Exception("Error while unpausing trip: " + e.getMessage());
        }
    }


    //GET ALL PAUSE BY SCOOTER
    public List<PauseOfScooterDto> getAllPauseByScooter() throws Exception {
        try {
            List<Pause> pauses = this.pauseRepository.getAllPauseByScooter();
            Map<Long, Duration> durationByScooter = new HashMap<>();

            for (Pause pause : pauses) {
                Long idScooter = pause.getTrip().getId_scooter();

                Instant startInstant = pause.getStart_date().toInstant();
                Instant endInstant = pause.getEnd_date().toInstant();

                Duration pauseDuration = Duration.between(startInstant, endInstant);

                durationByScooter.put(idScooter,
                        durationByScooter.getOrDefault(idScooter, Duration.ZERO).plus(pauseDuration));
            }

            List<PauseOfScooterDto> result = new ArrayList<>();
            for (Map.Entry<Long, Duration> entry : durationByScooter.entrySet()) {
                Long idScooter = entry.getKey();
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

    public List<PauseDto> getAllPauseByIdTrip(Long id_pause) throws Exception {
        try {
            List<Pause> pauses = this.pauseRepository.getAllPauseByIdTrip(id_pause);
            List<PauseDto> pausesDto = new ArrayList<>();
            for (Pause pause : pauses) {
                pausesDto.add(new PauseDto(pause.getTrip().getId_trip(), pause.getStart_date(), pause.getEnd_date()));
            }
            return pausesDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TripDto getTripByIdTrip(Long id_trip) throws Exception {
        try {
            Trip trip = tripRepository.findById(id_trip).get();
            return new TripDto(trip.getId_trip(), trip.getId_user(), trip.getId_account(), trip.getStart_date(), trip.getId_scooter(), trip.getEnd_date(), trip.getKm_traveled());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<TripDto> getTripByYearAndCountTrip(int year, int count) throws Exception {
        try {
            List<Trip> trips = tripRepository.getTripByYearAndCountTrip(year, count);
            List<TripDto> tripDtos = new ArrayList<>();
            for (Trip trip : trips) {
                tripDtos.add(new TripDto(trip.getId_trip(), trip.getId_user(), trip.getId_account(), trip.getStart_date(), trip.getId_scooter(), trip.getEnd_date(), trip.getKm_traveled()));
            }
            return tripDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private double calculateDistance(Double startLat, Double startLon, Double endLat, Double endLon) {
        double EARTH_RADIUS = 6371.0;
        double MAX_DISTANCE = 50.0;

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

        double distance = EARTH_RADIUS * c;
        return Math.min(distance, MAX_DISTANCE);
    }
}

