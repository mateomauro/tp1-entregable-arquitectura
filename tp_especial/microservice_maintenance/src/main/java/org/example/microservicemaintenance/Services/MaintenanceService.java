package org.example.microservicemaintenance.Services;

import org.example.microservicemaintenance.DTOs.MaintenanceDTO;
import org.example.microservicemaintenance.DTOs.MaintenanceReportDTO;
import org.example.microservicemaintenance.DTOs.MaintenanceScooterDTO;
import org.example.microservicemaintenance.FeignClient.Model.Pause;
import org.example.microservicemaintenance.FeignClient.Model.Scooter;
import org.example.microservicemaintenance.FeignClient.ScooterFeignClient;
import org.example.microservicemaintenance.FeignClient.TripFeignClient;
import org.example.microservicemaintenance.Repository.MaintenanceRepository;
import org.example.microservicemaintenance.Entities.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("MaintenanceService")
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceReposity;
    @Autowired
    private ScooterFeignClient scooterFeignClient;
    @Autowired
    private TripFeignClient tripFeignClient;

    private final double KM_MAX = 3500;
    private final double TIME_MAX = 350;
    //private final Duration TIME_MAX = Duration.ofHours(350);

    private MaintenanceDTO convertToDTO(Maintenance maintenance) {
        return new MaintenanceDTO(
                maintenance.getId_maintenance(),
                maintenance.getId_skateboard(),
                maintenance.isRepair()
        );
    }

    public List<MaintenanceScooterDTO> getAllScooterNotReapair() throws Exception{
        try {
            List<MaintenanceScooterDTO> mains = maintenanceReposity.findAllMaintenanceNotRepair();
            return mains;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MaintenanceDTO> findAll() throws Exception{
        try {
            List<Maintenance> mains = maintenanceReposity.findAll();
            return mains.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Reporte de mantenimiento con pausas incluidas
    public List<MaintenanceReportDTO> getReport(){
        List<Scooter> scooters = scooterFeignClient.getAllScooter();
        List<Pause> pausesByScooterInTrip = tripFeignClient.getAllTripPause();
        List<MaintenanceReportDTO> reportDTO = List.of();
        for (Scooter scooter: scooters){
            for (Pause pauses: pausesByScooterInTrip){
                if(scooter.getId_scooter() == pauses.getId_scooter()){
                    reportDTO.add(new MaintenanceReportDTO(scooter.getKm_traveled(), scooter.getUsage_time(),
                            scooter.getId_scooter(), pauses.getHours(), pauses.getMinutes()));
                }
            }
        }
        return reportDTO;
    }


    public List<MaintenanceDTO> checkMaintenance() throws Exception {
        List<Scooter> scooters = scooterFeignClient.getAllScooter();
        List<MaintenanceDTO> maintenances = List.of();
        for (Scooter scooter :scooters){
            if(scooter.getKm_traveled() > KM_MAX && scooter.getUsage_time() > TIME_MAX){
                //Guardo el scooter para el mantenimiento
                Maintenance maintenance = new Maintenance(false, scooter.getId_scooter());
                //Lo mando a mantenmiento
                this.save(maintenance);
                //Lo saco de servicio al monopatin
                this.scooterFeignClient.disableScooterMaintenance(scooter.getId_scooter());
                maintenances.add(new MaintenanceDTO(maintenance.getId_maintenance(),maintenance.getId_skateboard(), maintenance.isRepair()));
            }
        }
        return maintenances;
    }

    //Agrego los
    public void save(Maintenance maintenance) throws Exception {
        try {
            Maintenance main = maintenanceReposity.save(maintenance);
            if(main.isRepair()){
                maintenanceReposity.deleteById(main.getId_maintenance());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public MaintenanceDTO delete(long id) throws Exception{
        try{
            if(maintenanceReposity.existsById(id)){
                Maintenance main = maintenanceReposity.getById(id);
                maintenanceReposity.deleteById(id);
                return new MaintenanceDTO(main.getId_maintenance(), main.getId_skateboard(), main.isRepair());
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public MaintenanceDTO getOne(long id) throws Exception{
        try {
            Optional<Maintenance> main = maintenanceReposity.findById(id);
            if (main.isPresent()){
                return new MaintenanceDTO(main.get().getId_maintenance(), main.get().getId_skateboard(), main.get().isRepair());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public MaintenanceDTO update(long id, Maintenance maintenance) throws Exception {
        try{
            if(maintenanceReposity.existsById(id)){
                maintenanceReposity.updateOne(id ,maintenance.getId_skateboard() ,maintenance.isRepair());
                Maintenance main = maintenanceReposity.findById(id).get();
                return new MaintenanceDTO(main.getId_maintenance(), main.getId_skateboard(), main.isRepair());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }


}
