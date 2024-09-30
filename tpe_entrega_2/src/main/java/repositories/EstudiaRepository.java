package repositories;

import entities.Estudia;
import utils.JPAUtil;

import javax.persistence.EntityManager;

public class EstudiaRepository {
    private EntityManager em;

    public EstudiaRepository(){
        this.em = JPAUtil.getEntityManager();
    }

    public void matricularAlumnoAcarrera(Estudia estudia){
        em.getTransaction().begin();
        em.persist(estudia);
        em.getTransaction().commit();
    }
}
