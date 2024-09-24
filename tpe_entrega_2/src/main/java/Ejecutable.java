import entities.Alumno;
import entities.Carrera;
import entities.Estudia;
import entities.EstudiaPK;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import java.sql.SQLException;

public class Ejecutable {

    public static void main(String[] args) throws SQLException {
        JPAUtil jpu = new JPAUtil();
        EntityManager em = jpu.getEntityManager();

        Alumno a1 = new Alumno("Pedro","Ramirez",21,"Masculino",11555896,"Tandil",1555538);
        Carrera c1 = new Carrera("TUDAI");

        em.getTransaction().begin();
        em.persist(a1);
        em.persist(c1);

        em.flush();

        EstudiaPK epk = new EstudiaPK(a1.getId_Alumno(), c1.getId_Carrera());
        Estudia e1 = new Estudia(epk,a1,c1,false,5);

        em.persist(e1);

        em.getTransaction().commit();
        em.close();

        System.out.println(e1.getId());

        System.out.println("Ya termino");
    }
}
