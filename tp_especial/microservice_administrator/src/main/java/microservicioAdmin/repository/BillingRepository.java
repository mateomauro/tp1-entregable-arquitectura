package microservicioAdmin.repository;

import microservicioAdmin.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    //Facturacion en rango de meses de cierto año
    @Query("SELECT SUM(b.totalTrip) FROM Billing b WHERE EXTRACT(YEAR FROM b.date) = :year AND EXTRACT(MONTH FROM b.date) BETWEEN :monthOne AND :monthTwo")
    Double getBilledAmount(@Param("year") int year, @Param("monthOne") int monthOne, @Param("monthTwo") int monthTwo);
}
