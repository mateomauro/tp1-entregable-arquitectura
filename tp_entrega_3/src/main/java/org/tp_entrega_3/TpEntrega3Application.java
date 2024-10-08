package org.tp_entrega_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tp_entrega_3.Controllers.AlumnoController;
import org.tp_entrega_3.Models.Alumno;

@SpringBootApplication
public class TpEntrega3Application {

    public static void main(String[] args) {

        SpringApplication.run(TpEntrega3Application.class, args);
        Alumno a = new Alumno("a","b","tan",123,12,"ge",645);
        System.out.printf("Esta andando");
    }

}
