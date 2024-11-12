package org.example.microservice_parking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.microservice_parking.dtos.ParkingDto;
import org.example.microservice_parking.entities.Parking;
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

    public List<ParkingDto> findAll() throws Exception{
        try{
            List<Parking> parkings = this.parkingRepository.findAll();
            List<ParkingDto> parkingDtos = new ArrayList<>();
            for(Parking  parking:parkings){
                parkingDtos.add(new ParkingDto(parking.getId_parking(),parking.getLatitude(), parking.getLongitude()));
            }
            return parkingDtos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //REGISTER A PARKING
    public ParkingDto save(ParkingDto parkingDto) throws Exception {
        try{
            Parking parking = new Parking(parkingDto.getLatitude(), parkingDto.getLongitude());
            parkingRepository.save(parking);
            parkingDto.setId_parking(parking.getId_parking());
            return parkingDto;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //MODIFY A PARKING
    @Transactional
    public ParkingDto update(long id_parking, ParkingDto parking) throws Exception {
        try {
            parkingRepository.update(id_parking,parking.getLatitude(), parking.getLongitude());
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    // DELETE A PARKING
    public void delete(long id_parking) throws Exception {
        try {
            this.parkingRepository.deleteById(id_parking);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET PARKING BY LATITUD AND LONGITUD
    public ParkingDto getParkingByLatitudeAndLongitude(double latitude, double longitude) throws Exception {
        try {
            Parking parking = parkingRepository.getParkingByLatitudeAndLongitude(latitude, longitude);
            return new ParkingDto(parking.getId_parking(),parking.getLatitude(),parking.getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //GET PARKING BY ID
    public ParkingDto getParkingById(Long id_parking) throws Exception {
        try {
            Optional<Parking> parking = parkingRepository.findById(id_parking);
            return new ParkingDto(parking.get().getId_parking(), parking.get().getLatitude(), parking.get().getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
