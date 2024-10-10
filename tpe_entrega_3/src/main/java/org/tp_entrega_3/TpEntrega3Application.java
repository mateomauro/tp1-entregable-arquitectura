package org.tp_entrega_3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tp_entrega_3.Controllers.AlumnoController;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Utils.LoadDatabase;

@SpringBootApplication
public class TpEntrega3Application {
    @Autowired
    private LoadDatabase loadDatabase;

    public static void main(String[] args) {
        SpringApplication.run(TpEntrega3Application.class, args);
        System.out.printf("Esta andando");
    }

    @PostConstruct
    public void init() throws Exception {
        loadDatabase.loadAlumnos();
        loadDatabase.loadCarreras();
        loadDatabase.loadEstudia();
    }
}
