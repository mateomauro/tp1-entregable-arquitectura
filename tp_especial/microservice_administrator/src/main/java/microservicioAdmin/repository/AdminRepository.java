package microservicioAdmin.repository;

import microservicioAdmin.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface AdminRepository extends JpaRepository<Rate,Long> {
//van las querys y metodos q busquen en la base de datos


    //Facturacion en rango de meses de cierto año
    @Modifying
    @Query("SELECT SUM(r.price) FROM Rate r WHERE EXTRACT(YEAR FROM r.date) = :year AND EXTRACT(MONTH FROM r.date) BETWEEN :monthOne AND :monthTwo")
    Double getBilledAmount(@Param("year") int year, @Param("monthOne") int monthOne, @Param("monthTwo") int monthTwo);
}
