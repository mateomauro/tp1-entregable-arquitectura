package servicios;

import entities.Estudia;
import utils.JPAUtil;

import javax.persistence.EntityManager;

public class EstudiaServicio {
    private EntityManager em;

    public EstudiaServicio(){
        this.em = JPAUtil.getEntityManager();
    }

    public void matricularEstudianteAcarrera(Estudia estudia){
        em.getTransaction().begin();
        em.persist(estudia);
        em.getTransaction().commit();
    }



}
