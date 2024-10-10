package org.tpe_entrega_3.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpe_entrega_3.Repositories.CarreraRepository;
import org.tpe_entrega_3.dtos.CarreraDTO;
import org.tpe_entrega_3.Models.Carrera;

import java.util.*;

@Service("CarreraService")
public class CarreraService {
    @Autowired
    private CarreraRepository repository;

    public List<Carrera> findAll() throws Exception {
        try {
            return repository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Carrera findById(int id) throws Exception {
        try {
            return repository.getCarreraById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Carrera findByName(String name) throws Exception {
        try {
            return repository.getCarreraByName(name);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
    public List<CarreraDTO> getCarrerasConAlumnosInscriptos() throws Exception {
        try {
            return repository.getCarrerasConAlumnosInscriptos();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // h) generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    // presentar los años de manera cronológica.
    public List<CarreraDTO> getReporteCarreras() throws Exception {
        try {
            List<CarreraDTO> inscriptos = repository.getReporteCarrerasByInscriptos();
            List<CarreraDTO> egresados = repository.getReporteCarrerasByEgresados();
            return fucionarJPQL(inscriptos, egresados);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
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
