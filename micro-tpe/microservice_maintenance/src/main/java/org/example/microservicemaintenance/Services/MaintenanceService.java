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

import java.util.ArrayList;
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

    private final double KM_MAX = 10;
    private final double TIME_MAX = 1;

    private MaintenanceDTO convertToDTO(Maintenance maintenance) {
        return new MaintenanceDTO(
                maintenance.getId_maintenance(),
                maintenance.getId_scooter(),
                maintenance.isRepair()
        );
    }

    public List<MaintenanceScooterDTO> getAllScooterNotReapair() throws Exception {
        try {
            List<MaintenanceScooterDTO> mains = maintenanceReposity.findAllMaintenanceNotRepair();
            return mains;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MaintenanceDTO> findAll() throws Exception {
        try {
            List<Maintenance> mains = maintenanceReposity.findAll();
            return mains.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Reporte de mantenimiento con pausas incluidas o no
    public List<MaintenanceReportDTO> getReport(boolean includePauses) {
        List<Scooter> scooters = scooterFeignClient.getAllScooter();
        List<Pause> pausesByScooterInTrip = tripFeignClient.getAllTripPause();
        List<MaintenanceReportDTO> reportDTO = new ArrayList<>();
        System.out.println(includePauses);
        for (Scooter scooter : scooters) {
            for (Pause pauses : pausesByScooterInTrip) {
                if (scooter.getId_scooter() == pauses.getId_scooter()) {
                    if (includePauses) {
                        reportDTO.add(new MaintenanceReportDTO(scooter.getKm_traveled(), scooter.getUsage_time(),
                                scooter.getId_scooter(), pauses.getHours(), pauses.getMinutes()));

                    } else {
                        reportDTO.add(new MaintenanceReportDTO(scooter.getKm_traveled(), scooter.getUsage_time(),
                                scooter.getId_scooter()));
                    }
                }
            }
        }
        return reportDTO;
    }

    public List<MaintenanceDTO> checkMaintenance() throws Exception {
        List<Scooter> scooters = scooterFeignClient.getAllScooter();
        List<MaintenanceDTO> maintenances = new ArrayList<>();
        for (Scooter scooter : scooters) {
            if (scooter.getKm_traveled() > KM_MAX && scooter.getUsage_time() > TIME_MAX && scooter.isIn_maintenance() == false) {
                //Guardo el scooter para el mantenimiento
                //Lo mando a mantenmiento
                Maintenance maintenance = this.save(new Maintenance(scooter.isIn_maintenance(), scooter.getId_scooter()));
                //Lo saco de servicio al monopatin
                this.scooterFeignClient.disableScooterMaintenance(scooter.getId_scooter());
                maintenances.add(new MaintenanceDTO(maintenance.getId_maintenance(), maintenance.getId_scooter(), maintenance.isRepair()));
            }
        }
        if (maintenances.isEmpty()) {
            throw new Exception("No hay scooters que necesiten mantenimiento");
        }
        return maintenances;
    }

    private Maintenance save(Maintenance maintenance) throws Exception {
        try {
            Maintenance main = maintenanceReposity.save(maintenance);
            return main;
        } catch (Exception e) {
            System.out.println("Error en save: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public MaintenanceDTO delete(long id) throws Exception {
        try {
            if (maintenanceReposity.existsById(id)) {
                Maintenance main = maintenanceReposity.getById(id);
                maintenanceReposity.deleteById(id);
                return new MaintenanceDTO(main.getId_maintenance(), main.getId_scooter(), main.isRepair());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public MaintenanceDTO getOne(long id) throws Exception {
        try {
            Optional<Maintenance> main = maintenanceReposity.findById(id);
            if (main.isPresent()) {
                return new MaintenanceDTO(main.get().getId_maintenance(), main.get().getId_scooter(), main.get().isRepair());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public MaintenanceDTO update(long id, Maintenance maintenance) throws Exception {
        try {
            if (maintenanceReposity.existsById(id)) {
                maintenanceReposity.updateOne(id, maintenance.getId_scooter(), maintenance.isRepair());
                Maintenance main = maintenanceReposity.findById(id).get();
                if(main.isRepair()) {
                  scooterFeignClient.leaveMaintenance(main.getId_scooter());
                }
                return new MaintenanceDTO(main.getId_maintenance(), main.getId_scooter(), main.isRepair());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }


}
