package microservicioAdmin.services;

import jakarta.transaction.Transactional;
import microservicioAdmin.dto.BillingDTO;
import microservicioAdmin.dto.RateDTO;
import microservicioAdmin.dto.ReportCountScootersDTO;
import microservicioAdmin.entities.Billing;
import microservicioAdmin.entities.Rate;
import microservicioAdmin.feignClients.*;
import microservicioAdmin.feignClients.model.*;
import microservicioAdmin.repository.BillingRepository;
import microservicioAdmin.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private TripFeignClients tripFeignClients;
    @Autowired
    private MaintenanceFeignClients maintenanceFeignClients;
    @Autowired
    private AccountFeignClients accountFeignClients;
    @Autowired
    private ScooterFeignClients scooterFeignClients;
    @Autowired
    private ParkingFeignClients parkingFeignClients;
    @Autowired
    private PauseFeignClients pauseFeignClients;


    //TARIFAS:
    //Guardar tarifa pasada por parametro
    @Transactional
    public RateDTO saveRate(RateDTO rateNew) throws Exception {
        Rate rate = mapToRate(rateNew);
        try {
            rateRepository.save(rate);
            return new RateDTO(rate.getIdRate(), rate.getPrice(), rate.getPriceForPause(), rate.getDate());
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
    public RateDTO updateRate(Long idRate, Rate rateNew) throws Exception {
        try {
            if (rateRepository.existsById(idRate)) {
                Rate r = this.rateRepository.save(rateNew);
                return new RateDTO(idRate, r.getPrice(), r.getPriceForPause(), r.getDate());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    //Eliminar una tarifa
    @Transactional
    public RateDTO deleteRate(Long id) throws Exception {
        Optional<Rate> rateDTO = rateRepository.findById(id);
        try {
            if (rateDTO.isPresent()) { // isPresent ve si el optional trajo algo
                Rate r = rateDTO.get();
                RateDTO rDTO = new RateDTO();
                rateRepository.delete(r);
                return rDTO;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    //Devolver todas las tarifas
    @Transactional
    public List<RateDTO> findAll() throws Exception {
        try {
            List<Rate> rates = rateRepository.findAll();
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

// BILLING:
    @Transactional
    public BillingDTO saveBilling(Billing billing) throws Exception {
        try {
            if (billingRepository.existsById(billing.getId_billing())) {
                Billing billingNew = this.billingRepository.save(billing);
                return new BillingDTO(billingNew.getId_billing(), billingNew.getDate(), billingNew.getTotalTrip());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
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
        try {
            return scooterFeignClients.findAllScooters();
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
        try {
            return parkingFeignClients.getAllParkings();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // traerme todos las paradas
    public ParkingDTO findParkingById(Long id) throws Exception {
        try {
            return parkingFeignClients.getById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

//OTROS METODOS:

    //Calcular el costo total del viaje
    public RateDTO calculateRateOfTrip(Long idTrip) throws Exception {
        try {
            TripDTO tripDTO = tripFeignClients.getTripById(idTrip);
            if (tripDTO != null) {
                Rate rateNew = this.rateRepository.getLastRate();
                int kmTrip = tripDTO.getKm_traveled();
                List<PauseDTO> pauseDTOs = pauseFeignClients.getAllPauseByIdTrip(idTrip);
                int timePause = 0;
                for (PauseDTO pauseDTO : pauseDTOs) {
                    Instant startInstant = pauseDTO.getStart_date().toInstant();
                    Instant endInstant = pauseDTO.getEnd_date().toInstant();
                    timePause += Duration.between(startInstant, endInstant).toMinutesPart();
                }
                double price = rateNew.getPrice() * kmTrip;
                rateNew.setPrice(price);
                if (timePause > 15)
                    rateNew.setPrice(price + ((timePause / 15) * rateNew.getPriceForPause()));
                this.billingRepository.save(new Billing(LocalDate.now(), rateNew.getPrice()));
                return new RateDTO(rateNew.getIdRate(), rateNew.getPrice(), rateNew.getPriceForPause(), rateNew.getDate());
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
            return billingRepository.getBilledAmount(year, monthOne, monthTwo);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //ajuste de precios a partir de cierta fecha
    @Transactional
    public RateDTO updateRateForDate(LocalDate date, Rate rateNew) throws Exception {
        try {
            if (!date.isBefore(LocalDate.now())) {
                rateRepository.save(rateNew);
                return new RateDTO(rateNew.getIdRate(), rateNew.getPrice(), rateNew.getPriceForPause(), rateNew.getDate());
            }
        } catch (Exception e) {
            throw new Exception("Error al actualizar la tarifa: " + e.getMessage());
        }
        return null;
    }

    //monopatines con más de X viajes en un cierto año.
    public List<ScooterDTO> fetchScootersByTripsInYear(int year, int countTrip) throws Exception {
        List<ScooterDTO> scooterForTripByYear = new ArrayList<>();
        try {
            List<TripDTO> moreForTripByYear = tripFeignClients.tripByYearAndCountTrip(year, countTrip);
            if (!moreForTripByYear.isEmpty()) {
                for (TripDTO trip : moreForTripByYear) {
                    ScooterDTO scooter = scooterFeignClients.getScooterById(trip.getId_scooter());
                    if (scooter != null) {
                        scooterForTripByYear.add(scooter);
                    }
                }
            }
            return scooterForTripByYear;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //cantidad de monopatines actualmente en operación,
    //versus la cantidad de monopatines actualmente en mantenimiento.
    public ReportCountScootersDTO countScootersInOperationAndMaintenance() throws Exception {
        int countActivs = scooterFeignClients.getActivs().size();
        int countMaintenance = maintenanceFeignClients.getMaintenances().size();
        return new ReportCountScootersDTO(countActivs, countMaintenance);
    }
}