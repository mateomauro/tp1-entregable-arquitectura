package org.tp_entrega_3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tp_entrega_3.Repositories.CarreraRepository;
import org.tp_entrega_3.dtos.CarreraDTO;

import java.util.*;
@RestController
@RequestMapping("/carreras")
public class CarreraController {
    @Autowired
    private final CarreraRepository repository;

    public CarreraController(CarreraRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getReporteCarreras")
    public List<CarreraDTO> getReporteCarreras(){
        List<CarreraDTO> inscriptos = repository.getReporteCarrerasByInscriptos();
        List<CarreraDTO> egresados = repository.getReporteCarrerasByEgresados();
        return fucionarJPQL(inscriptos, egresados);
    }

    private List<CarreraDTO> fucionarJPQL(List<CarreraDTO> inscriptos, List<CarreraDTO> egresados) {
        //Aqui creamos el mapa para guardar los resultado finales (osea las combinacion final)
        Map<String, CarreraDTO> resultadoMap = new HashMap<>();

        //recorremos los inscriptos
        for (CarreraDTO inscrip : inscriptos) {
            //generamos una key que es la combinacion de el nombre de la carrera y el anio = tudai_2020
            String key = inscrip.getNombre_carrera() + "_" + inscrip.getAnio();
            CarreraDTO dto = new CarreraDTO(inscrip.getNombre_carrera(), inscrip.getAnio(), inscrip.getCant_inscriptos());
            resultadoMap.put(key, dto);
        }

        /*
        aqui recorremos los egresados y cuando encuentra en el hashMap un clave igual
        le setea los egresado, el caso contrario agrega un nuevo dto al map con inscriptos 0
         */
        for (CarreraDTO egresa : egresados) {
            String key = egresa.getNombre_carrera() + "_" + egresa.getAnio();
            if (resultadoMap.containsKey(key)) {
                CarreraDTO dto = resultadoMap.get(key);
                dto.setEgresados(egresa.getEgresados());
            } else {
                CarreraDTO dto = new CarreraDTO(egresa.getNombre_carrera(), 0, egresa.getEgresados(), egresa.getAnio());
                resultadoMap.put(key, dto);
            }
        }

        List<CarreraDTO> resultado = new ArrayList<>(resultadoMap.values());

        //los ordenamos primero por nombre y luego por anio
        Collections.sort(resultado, (o1, o2) -> {
            int comparison = o1.getNombre_carrera().compareTo(o2.getNombre_carrera());
            if (comparison != 0) {
                return comparison;
            }
            return Integer.compare(o1.getAnio(), o2.getAnio());
        });

        return resultado;
    }
}
