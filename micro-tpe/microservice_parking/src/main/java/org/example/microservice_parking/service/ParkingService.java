package org.example.microservice_parking.service;

import lombok.RequiredArgsConstructor;
import org.example.microservice_parking.dtos.ParkingDto;
import org.example.microservice_parking.entities.Counter;
import org.example.microservice_parking.entities.Parking;
import org.example.microservice_parking.repository.CounterRepository;
import org.example.microservice_parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingService {
    @Autowired
    private final ParkingRepository parkingRepository;

    @Autowired
    private CounterRepository counterRepository;

    public List<ParkingDto> findAll() throws Exception {
        try {
            List<Parking> parkings = this.parkingRepository.findAll();
            List<ParkingDto> parkingDtos = new ArrayList<>();
            for (Parking parking : parkings) {
                parkingDtos.add(new ParkingDto(parking.getId_parking(),parking.getLatitude(), parking.getLongitude()));
            }
            return parkingDtos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //REGISTER A PARKING
    public ParkingDto save(ParkingDto parkingDto) throws Exception {
        try {
            Parking parking = new Parking(this.getNextParkingId(), parkingDto.getLatitude(), parkingDto.getLongitude());
            parking = parkingRepository.save(parking);
            parkingDto.setId_parking(parking.getId_parking());
            return parkingDto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    //MODIFY A PARKING
    public ParkingDto update(Long id_parking, ParkingDto parking) throws Exception {
        try {
            Parking park = parkingRepository.findById(id_parking).orElse(null);
            if(park != null) {
                park.setLatitude(parking.getLatitude());
                park.setLongitude(parking.getLongitude());
                parkingRepository.save(park);
            }
            return new ParkingDto(id_parking, parking.getLatitude(), parking.getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    // DELETE A PARKING
    public ParkingDto delete(Long id_parking) throws Exception {
        try {
            Parking parking = parkingRepository.findById(id_parking).get();
            this.parkingRepository.deleteById(id_parking);
            return new ParkingDto(parking.getId_parking(), parking.getLatitude(), parking.getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET PARKING BY LATITUD AND LONGITUD
    public ParkingDto getParkingByLatitudeAndLongitude(Double latitude, Double longitude) throws Exception {
        try {
            Parking parking = parkingRepository.getParkingByLatitudeAndLongitude(latitude, longitude);
            return new ParkingDto(parking.getId_parking(), parking.getLatitude(), parking.getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET PARKING BY ID
    public ParkingDto getParkingById(Long id_parking) throws Exception {
        try {
            Parking parking = parkingRepository.findById(id_parking).get();
            return new ParkingDto(parking.getId_parking(), parking.getLatitude(), parking.getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Long getNextParkingId() {
        Counter counter = counterRepository.findById("parkingId").orElse(new Counter("parkingId", 1L));
        if (counter.getSeq() == null) {
            counter.setSeq(1L);
        } else {
            counter.setSeq(counter.getSeq() + 1);
        }
        counterRepository.save(counter);

        return counter.getSeq();
    }


}
