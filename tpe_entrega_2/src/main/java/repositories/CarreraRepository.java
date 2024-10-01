package repositories;

import dtos.CarreraDTO;
import entities.Alumno;
import entities.Carrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CarreraRepository {
    private EntityManager em;

    public CarreraRepository(){
        this.em = JPAUtil.getEntityManager();
    }

    public void darAltaCarrera(Carrera carrera){
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    public Carrera getCarreraById(long id){
        return em.find(Carrera.class, id);
    }

    public List<CarreraDTO> getCarrerasConAlumnosInscriptos(){
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.CarreraDTO(c.id, c.nombre, COUNT(e.alumno.id)) FROM Carrera c JOIN c.alumnos e GROUP BY c.id, c.nombre ORDER BY COUNT(e.alumno.id) DESC";
        TypedQuery<CarreraDTO> TypedQuery = em.createQuery(jpql, CarreraDTO.class);
        List<CarreraDTO> carreras = TypedQuery.getResultList();
        em.getTransaction().commit();
        return carreras;
    }


    /*
    Aqui hicimos dos consultas jpql como te comentamos sergio, en las cuales luego combinamos
    con codigo java
     */
    public List<CarreraDTO> getReporteCarreras(){
        em.getTransaction().begin();
        String jpqlXinscriptos = "SELECT new dtos.CarreraDTO(c.nombre, e.anio_ingreso, COUNT(e)) FROM Carrera c JOIN c.alumnos e GROUP BY c.nombre, e.anio_ingreso ORDER BY c.nombre, e.anio_ingreso";
        TypedQuery<CarreraDTO> TypedQuery1 = em.createQuery(jpqlXinscriptos, CarreraDTO.class);
        List<CarreraDTO> inscriptos = TypedQuery1.getResultList();

        String jpqlXegresados = "SELECT new dtos.CarreraDTO(c.nombre, COUNT(e),e.anio_graduacion) FROM Carrera c JOIN c.alumnos e WHERE e.anio_graduacion > 0 GROUP BY c.nombre,e.anio_graduacion ORDER BY c.nombre, e.anio_graduacion";
        TypedQuery<CarreraDTO> TypedQuery2 = em.createQuery(jpqlXegresados, CarreraDTO.class);
        List<CarreraDTO> egresados = TypedQuery2.getResultList();

        em.getTransaction().commit();

        return this.fucionarJPQL(inscriptos, egresados);
    }

    //aqui las combinamos
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
