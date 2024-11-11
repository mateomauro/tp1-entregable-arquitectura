package com.tpe.micro.scooters.Services;

import com.tpe.micro.scooters.DTOs.ScooterRequestDTO;
import com.tpe.micro.scooters.DTOs.ScooterResponseDTO;
import com.tpe.micro.scooters.Entities.Scooter;
import com.tpe.micro.scooters.Repositories.ScooterRepository;
import com.tpe.micro.scooters.Services.Exceptions.NoScootersException;
import com.tpe.micro.scooters.Services.Exceptions.NoScootersFoundException;
import com.tpe.micro.scooters.Services.Exceptions.ScooterNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScooterService{

    @Autowired
    ScooterRepository scooterRepo;

    @Transactional(readOnly = true)
    public List<ScooterResponseDTO> getAll(){
        List<Scooter> scooters = scooterRepo.findAll();

        if(scooters.isEmpty()){
            throw new NoScootersException("");
        }else{
            return scooters.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
        }
    }

    private ScooterResponseDTO mapToResponseDTO(Scooter scooter) {

        ScooterResponseDTO responseDTO = new ScooterResponseDTO();

        responseDTO.setId_scooter(scooter.getId_scooter());
        responseDTO.setLongitude(scooter.getLongitude());
        responseDTO.setLatitude(scooter.getLatitude());
        responseDTO.setQR_Code(scooter.getQR_Code());
        responseDTO.setAvailable(scooter.isAvailable());
        responseDTO.setIn_maintenance(scooter.isIn_maintenance());
        responseDTO.setKm_traveled(scooter.getKm_traveled());
        responseDTO.setUsage_time(scooter.getUsage_time());

        return responseDTO;
    }

    @Transactional(readOnly = true)
    public ScooterResponseDTO getById(Long id) throws RuntimeException{
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);
        if(scooter == null){
            throw new ScooterNotFoundException("",id);
        }else{
            return this.mapToResponseDTO(scooter);
        }
    }

    @Transactional
    public ScooterResponseDTO insert(@Valid ScooterRequestDTO scooterRequestDTO) throws RuntimeException{
        Scooter scooter = new Scooter(scooterRequestDTO.getLatitude(),scooterRequestDTO.getLongitude(),scooterRequestDTO.getQR_Code());
        this.scooterRepo.save(scooter);
        return this.mapToResponseDTO(scooter);
    }

    @Transactional
    public ScooterResponseDTO update(long id,@Valid ScooterRequestDTO scooterRequestDTO) throws ScooterNotFoundException{
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);

        // Valido que exista el scooter
        if(scooter == null){
            throw new ScooterNotFoundException("",id);
        }else {
            updateValues(scooter,scooterRequestDTO);
            this.scooterRepo.save(scooter);
            return this.mapToResponseDTO(scooter);
        }
    }

    private Scooter updateValues(Scooter scooter, ScooterRequestDTO scooterRequestDTO){
        scooter.setLatitude(scooterRequestDTO.getLatitude());
        scooter.setLongitude(scooterRequestDTO.getLongitude());
        scooter.setQR_Code(scooterRequestDTO.getQR_Code());
        scooter.setAvailable(scooterRequestDTO.isAvailable());
        scooter.setIn_maintenance(scooterRequestDTO.isIn_maintenance());
        scooter.setUsage_time(scooterRequestDTO.getUsage_time());
        scooter.setKm_traveled(scooterRequestDTO.getKm_traveled());
        return scooter;
    }

    @Transactional
    public ScooterResponseDTO delete(long id) throws Exception{
        // Obtengo el scooter a eliminar
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);

        // Valido que exista el scooter
        if(scooter == null){
            throw new ScooterNotFoundException("",id);
        }else {
            this.scooterRepo.delete(scooter);
            return this.mapToResponseDTO(scooter);
        }
    }

    @Transactional
    public ScooterResponseDTO activateScooter(long id) {
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);

        if(scooter == null){
            throw new ScooterNotFoundException("", id);
        }else{
            scooter.setAvailable(true);
            scooter.setIn_maintenance(false);
            this.scooterRepo.save(scooter);
            return this.mapToResponseDTO(scooter);
        }
    }

    @Transactional
    public ScooterResponseDTO deactivateScooter(long id) {
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);

        if(scooter == null){
            throw new ScooterNotFoundException("",id);
        }else{
            scooter.setAvailable(false);
            scooter.setIn_maintenance(true);
            this.scooterRepo.save(scooter);
            return this.mapToResponseDTO(scooter);
        }
    }

    // Devuelve una lista de los monopatines cercanos a esa latitud y longitud, teniendo en cuenta el radio solicitado
    @Transactional
    public List<ScooterResponseDTO> findNearby(Double latitude, Double longitude, Double radius) {

        Double maxLongitude = longitude + radius;
        Double minLongitude = longitude - radius;
        Double maxLatitud = latitude + radius;
        Double minLatitud = latitude - radius;

        List<Scooter> scooters = this.scooterRepo.findNearby(minLongitude,maxLongitude,minLatitud,maxLatitud);

        if(scooters.isEmpty()){
            throw new NoScootersException("");
        }else{
            return scooters.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
        }
    }

    // Devuelve el monopatin mas cercano a esa latitud y longitud
    @Transactional
    public ScooterResponseDTO findAScooterNear(Double latitude, Double longitude) {
        Scooter scooter = this.scooterRepo.findAScooterNear(latitude, longitude);
        if(scooter == null){
            throw new NoScootersFoundException("");
        }else{
            return this.mapToResponseDTO(scooter);
        }
    }

    // Devuelve la lista de monopatines ordenados por mas kilometros recorridos de mayor a menor
    @Transactional
    public List<ScooterResponseDTO> getAllByKm() {
        List<Scooter> scooters = this.scooterRepo.findByKm();

        if(scooters.isEmpty()){
            throw new NoScootersFoundException("");
        }else{
            return scooters.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
        }
    }

    @Transactional
    public ScooterResponseDTO addToMaintenance(long id) {
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);

        if(scooter == null){
            throw new ScooterNotFoundException("",id);
        }else{
            scooter.setIn_maintenance(true);
            scooter.setAvailable(false);
            this.scooterRepo.save(scooter);
            return this.mapToResponseDTO(scooter);
        }
    }

    @Transactional
    public ScooterResponseDTO leaveMaintenance(long id) {
        Scooter scooter = this.scooterRepo.findById(id).orElse(null);

        if(scooter == null){
            throw new ScooterNotFoundException("",id);
        }else{
            scooter.setIn_maintenance(false);
            scooter.setAvailable(true);
            this.scooterRepo.save(scooter);
            return this.mapToResponseDTO(scooter);
        }
    }

    @Transactional
    public List<ScooterResponseDTO> getActives() {
        List<Scooter> scooters = this.scooterRepo.getActives();

        if(scooters == null){
            throw new NoScootersFoundException("");
        }else{
            return scooters.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
        }
    }
}
