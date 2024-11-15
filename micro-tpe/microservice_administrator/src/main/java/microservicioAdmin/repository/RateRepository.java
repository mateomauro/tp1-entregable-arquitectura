package microservicioAdmin.repository;

import microservicioAdmin.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;


public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT r FROM Rate r WHERE r.date <= :currentDate ORDER BY r.idRate DESC LIMIT 1")
    Rate getLastRate(@Param("currentDate") LocalDate currentDate);

    @Modifying
    @Query("UPDATE Rate r SET r.price = :price, r.priceForPause = :priceForPause, r.date = :date WHERE r.idRate = :idRate")
    void updateRate(@Param("idRate") Long idRate, @Param("price") Double price, @Param("priceForPause") Double priceForPause, @Param("date") LocalDate date);
}
