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

    public void createDatabase() {
        String nameDB = "entregable2";
        //tring createDatabase = "CREATE DATABASE IF NOT EXISTS " + nameDB;
        // aquí volvemos a pisar la URI con el nombre de la base de datos y pedimos nuevamente la conexión.
        em.getTransaction().begin();
        em.createNativeQuery("CREATE O REPLACE DATABASE IF NOT EXIST"+nameDB).executeUpdate();
        em.getTransaction().commit();
        //closeConnection();
    }

    /**
     * No necesito crear las tablas, las crea hibernate, si necesito que este creado el schema "integrador2"
     *
     * public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS cliente(" +
                "idCliente INT NOT NULL AUTO_INCREMENT, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente));";

        em.getTransaction().begin();
        em.createNativeQuery(tableCliente).executeUpdate();
        em.getTransaction().commit();

        String tableFactura = "CREATE TABLE IF NOT EXISTS factura(" +
                "idFactura INT NOT NULL AUTO_INCREMENT, " +
                "idCliente INT NOT NULL, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
                "CONSTRAINT FK_cliente FOREIGN KEY (idCliente) REFERENCES cliente (idCliente));";
        em.getTransaction().begin();
        em.createNativeQuery(tableFactura).executeUpdate();
        em.getTransaction().commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS producto(" +
                "idProducto INT NOT NULL AUTO_INCREMENT, " +
                "nombre VARCHAR(45) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));";
        em.getTransaction().begin();
        em.createNativeQuery(tableProducto).executeUpdate();
        em.getTransaction().commit();

        String tableFactura_Producto = "CREATE TABLE IF NOT EXISTS factura_producto(" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_Factura_Producto FOREIGN KEY (idProducto) REFERENCES producto (idProducto), " +
                "CONSTRAINT FK_Factura FOREIGN KEY (idFactura) REFERENCES factura (idFactura));";
        em.getTransaction().begin();
        em.createNativeQuery(tableFactura_Producto).executeUpdate();
        em.getTransaction().commit();
        closeConnection();
    }
     */

    public void closeConnection() {
        if (em != null) {
            em.close();
            emf.close();
        }
    }
}
