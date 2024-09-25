package servicio;

import entities.Alumno;
import entities.Carrera;
import utils.JPAUtil;

import javax.persistence.EntityManager;
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


}
