package microservicioAdmin.services;

import jakarta.transaction.Transactional;
import microservicioAdmin.dto.RateDTO;
import microservicioAdmin.dto.ReportCountScootersDTO;
import microservicioAdmin.entities.Rate;
import microservicioAdmin.feignClients.*;
import microservicioAdmin.feignClients.model.*;
import microservicioAdmin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("AdminService")
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private TripFeignClients tripFeignClients;
    private MaintenanceFeignClients maintenanceFeignClients;
    private AccountFeignClients accountFeignClients;
    private ScooterFeignClients scooterFeignClients;
    private ParkingFeignClients parkingFeignClients;
    private PauseFeignClients pauseFeignClients;

    //TARIFAS:
    //Guardar tarifa pasada por parametro
    public RateDTO saveRate(RateDTO rateNew) throws Exception {
        Rate rate = mapToRate(rateNew);
        try {
            adminRepository.save(rate);
            return new RateDTO();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Rate mapToRate(RateDTO rateDTO) throws Exception {
        Rate rate = new Rate();
        rate.setPrice(rateDTO.getPrice());
        rate.setPriceForPause(rateDTO.getPriceForPause());
        return rate;
    }

    //Actualizar precio de la tarifa
    @Transactional //en caso de errores se revierten los datos
    public RateDTO updateRate(Long idRate, RateDTO rateNew) throws Exception {
        try {
            if (adminRepository.existsById(idRate)) {
                adminRepository.updateRate(idRate, rateNew.getPrice(), rateNew.getPriceForPause());
                return new RateDTO(idRate, rateNew.getPrice(), rateNew.getPriceForPause());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    //Eliminar una tarifa
    @Transactional
    public RateDTO deleteRate(Long id) throws Exception {
        Optional<Rate> rateDTO = adminRepository.findById(id);
        try {
            if (rateDTO.isPresent()) { // isPresent ve si el optional trajo algo
                Rate r = rateDTO.get();
                RateDTO rDTO = new RateDTO();
                adminRepository.delete(r);
                return rDTO;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    //Devolver todas las tarifas
    public List<RateDTO> findAll() throws Exception {
        try {
            List<Rate> rates = adminRepository.findAll();
            List<RateDTO> ratesNew = new ArrayList<>();
            for (Rate r : rates) {
                RateDTO rateDTO = new RateDTO();
                rateDTO.setPrice(r.getPrice());
                rateDTO.setPriceForPause(r.getPriceForPause());
                ratesNew.add(rateDTO);
            }
            return ratesNew;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//MONOPATIN:

    //Agregar monopatin
    public ScooterDTO insertScooter(ScooterDTO scooterNew) throws Exception {
        try {
            return scooterFeignClients.insertScooter(scooterNew);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Editar monopatin
    @Transactional
    public ScooterDTO updateScooter(Long idScooter, ScooterDTO scooterDTO) throws Exception {
        try {
            return scooterFeignClients.updateScooter(idScooter, scooterDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Eliminar un monopatin con el id parasado por parametro
    public ScooterDTO deleteScooter(Long idScooter) throws Exception {
        try {
            return scooterFeignClients.deleteScooter(idScooter);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // traerme todos los monopatines
    public List<ScooterDTO> findAllScooter() throws Exception {
        List<ScooterDTO> scooterDTOs = new ArrayList<>();
        try {
            scooterDTOs = scooterFeignClients.findAllScooters();
            return scooterDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//ESTACIONAMIENTOS

    //insertar parada
    public ParkingDTO insertParking(ParkingDTO parkingNew) throws Exception {
        try {
            return parkingFeignClients.insertParking(parkingNew);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Editar parada
    @Transactional
    public ParkingDTO updateParking(Long idParking, ParkingDTO parkingDTO) throws Exception {
        try {
                parkingFeignClients.updateParking(idParking, parkingDTO);

                return parkingFeignClients.getById(idParking);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Eliminar un parada con el id pasado por parametro
    public ParkingDTO deleteParking(Long idParking) throws Exception {
        try {
            parkingFeignClients.deleteParking(idParking);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    // traerme todos las paradas
    public List<ParkingDTO> findAllParking() throws Exception {
        List<ParkingDTO> parkingDTOs = new ArrayList<>();
        try {
            parkingDTOs = parkingFeignClients.getAllParkings();
            return parkingDTOs;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//OTROS METODOS:

    //Calcular el costo total del viaje
    public RateDTO calculateRateOfTrip(Long idTrip) throws Exception {
        try {
            if (tripFeignClients.getTripById(idTrip) != null) {
                Rate rateNew = new Rate();
                double kmTrip = tripFeignClients.getTripById(idTrip).getKm_traveled();
                List<PauseDTO> pauseDTOs = pauseFeignClients.getAllPauseByIdTrip(idTrip) ;
                if (pauseDTOs.isEmpty()) {
                    rateNew.setPrice(rateNew.getPrice() * kmTrip);
                } else {
                    rateNew.setPriceForPause(rateNew.getPriceForPause() * kmTrip);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    //Anular Cuenta de usuario
    public AccountDTO annulledAccount(Long idAccount, boolean annul) throws Exception {
        try {
            return accountFeignClients.annulledAccount(idAccount, annul);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Facturacion en rango de meses de cierto año
    public double getBilledAmount(int year, int monthOne, int monthTwo) throws Exception {
        try {
            return adminRepository.getBilledAmount(year, monthOne, monthTwo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    //ajuste de precios a partir de cierta fecha
    @Transactional
    public RateDTO updateRateForDate(LocalDate date, RateDTO rateNew) throws Exception {
        try {
            if (!date.isBefore(LocalDate.now())) {
                adminRepository.updateRate(rateNew.getIdRate(), rateNew.getPrice(), rateNew.getPriceForPause());
                return new RateDTO(rateNew.getIdRate(), rateNew.getPrice(), rateNew.getPriceForPause());
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar la tarifa: " + e.getMessage());
        }
        return null;
    }

    //monopatines con más de X viajes en un cierto año.
    public List<ScooterDTO> fetchScootersByTripsInYear(int year, int countTrip) throws Exception {
        try {
            List<TripDTO> moreForTripByYear = tripFeignClients.tripByYearAndCountTrip(year, countTrip);
            if (!moreForTripByYear.isEmpty())
                for (TripDTO trip : moreForTripByYear) {
                    ScooterDTO scooter = scooterFeignClients.getScooterById(trip.getId_scooter());
                    List<ScooterDTO> scooperForTripByYear = new ArrayList<>();
                    if (scooter != null) {
                        scooperForTripByYear.add(scooter);
                    }
                }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    //cantidad de monopatines actualmente en operación,
    //versus la cantidad de monopatines actualmente en mantenimiento.
    public ReportCountScootersDTO countScootersInOperationAndMaintenance() throws Exception {
        int countActivs = scooterFeignClients.getActivs().size();
        int countMaintenance = maintenanceFeignClients.getMaintenances().size();
        return new ReportCountScootersDTO(countActivs, countMaintenance);
    }
}