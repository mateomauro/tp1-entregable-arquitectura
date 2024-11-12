package microservicioAdmin.repository;

import microservicioAdmin.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RateRepository extends JpaRepository<Rate,Long> {
//van las querys y metodos q busquen en la base de datos

    @Query("SELECT r FROM Rate r ORDER BY r.idRate DESC LIMIT 1")
    Rate getLastRate();


}
