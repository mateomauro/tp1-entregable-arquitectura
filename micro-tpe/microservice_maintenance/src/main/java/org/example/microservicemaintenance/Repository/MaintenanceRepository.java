package org.example.microservicemaintenance.Repository;

import org.example.microservicemaintenance.DTOs.MaintenanceScooterDTO;
import org.example.microservicemaintenance.Entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("MaintenanceRepository")
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Maintenance m SET m.id_scooter = :id_skateboard, m.repair = :repair WHERE m.id_maintenance = :id")
    public void updateOne(@Param("id") long id, @Param("id_skateboard") long id_skateboard, @Param("repair") Boolean repair);

    @Transactional
    @Query("SELECT new org.example.microservicemaintenance.DTOs.MaintenanceScooterDTO(m.id_scooter,m.repair) FROM Maintenance m WHERE m.repair = false")
    public List<MaintenanceScooterDTO> findAllMaintenanceNotRepair();

}
