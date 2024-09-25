package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JPAUtil {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public JPAUtil(){
        getEntityManager();
    }

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
