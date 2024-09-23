package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public JPAUtil(){}

    public synchronized static EntityManager getEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("Integrador");
            em = emf.createEntityManager();
        }
        return em;
    }

    public void closeConnection() {
        if (em != null) {
            em.close();
            emf.close();
        }
    }
}
