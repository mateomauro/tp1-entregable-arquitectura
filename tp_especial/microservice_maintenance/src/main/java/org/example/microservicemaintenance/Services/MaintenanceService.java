package org.example.microservicemaintenance.Services;

import org.example.microservicemaintenance.DTOs.MaintenanceDTO;
import org.example.microservicemaintenance.DTOs.MaintenanceReportDTO;
import org.example.microservicemaintenance.DTOs.MaintenanceScooterDTO;
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
    //@Autowired
    //private ScooterFeingClient scooterFiengClient;
    //@Autowired
    //private TripFeignClient tripFeignClient;

    private final double KM_MAX = 3500;
    private final Duration TIEMPO_MAX = Duration.ofHours(350);

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

    public List<MaintenanceReportDTO> getReport(){
        //Tendria que filtrar o no pausas con query param
        //Llamo al servicio scooter y trip
        //Asi genero el reporte monopatin x km,tiempo uso y tiempo de uso
        return null;
    }

    public MaintenanceDTO save(Maintenance maintenance) throws Exception {
        try {
            long skateBoard = maintenance.getId_skateboard();
            //Aca tendria que buscar si existe el monopatin relacionando con el servicio
            //Ademas tendria que preguntar si el KM_MAX > km(que viene del monopatin) y el tiempo lo mismo
            if(skateBoard != 0) { //En este if llamo al metodo
                Maintenance main = maintenanceReposity.save(maintenance);
                //Si el monopatin fue reparado lo borro
                //if(main.isRepair()){ maintenanceReposity.deleteById(main.getId_maintenance());}
                return new MaintenanceDTO(main.getId_maintenance(),main.getId_skateboard(), main.isRepair());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
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
