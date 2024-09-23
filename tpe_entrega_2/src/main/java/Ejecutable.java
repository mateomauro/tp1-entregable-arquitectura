import utils.JPAUtil;

import javax.persistence.EntityManager;

public class Ejecutable {

    public static void main(String[] args) {
        JPAUtil jpu = new JPAUtil();
        EntityManager em = jpu.getEntityManager();
        jpu.closeConnection();
        System.out.println("Ya termino");
    }
}
