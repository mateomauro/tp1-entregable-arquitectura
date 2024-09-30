package servicios;

import dtos.CarreraDTO;
import entities.Alumno;
import entities.Carrera;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class CarreraServicio {
    private EntityManager em;

    public CarreraServicio(){
        this.em = JPAUtil.getEntityManager();
    }

    public void darAltaCarrera(Carrera carrera){
        em.getTransaction().begin();
        em.persist(carrera);
        em.getTransaction().commit();
    }

    public Carrera getCarreraXid(long id){
        return em.find(Carrera.class, id);
    }

    public List<CarreraDTO> getCarrerasConCantidad(){
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.CarreraDTO(c.id, c.nombre, COUNT(e.alumno.id)) FROM Carrera c JOIN c.alumnos e GROUP BY c.id, c.nombre ORDER BY COUNT(e.alumno.id) DESC";
        TypedQuery<CarreraDTO> TypedQuery = em.createQuery(jpql, CarreraDTO.class);
        List<CarreraDTO> carreras = TypedQuery.getResultList();
        em.getTransaction().commit();
        return carreras;
    }


     //   3) Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados
     //      por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.

    public List<CarreraDTO> getReporteCarreras(){
        em.getTransaction().begin();
        String jpql = "SELECT new dtos.CarreraDTO(c.nombre,COUNT(e.seGraduo), YEAR(e.anio_graduacion)) FROM Carrera c JOIN c.alumnos e GROUP BY c.nombre,e.anio_graduacion ORDER BY c.nombre ASC, e.anio_graduacion DESC";
        TypedQuery<CarreraDTO> TypedQuery = em.createQuery(jpql, CarreraDTO.class);
        List<CarreraDTO> carreras = TypedQuery.getResultList();
        em.getTransaction().commit();
        return carreras;
    }
    //c.alumnos

    // TUDAI | 2010 | 10 inscriptos actualmente | egresados : (juan, pepito, carlitos, maria) |

}
