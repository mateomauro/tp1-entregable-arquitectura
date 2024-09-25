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

}
