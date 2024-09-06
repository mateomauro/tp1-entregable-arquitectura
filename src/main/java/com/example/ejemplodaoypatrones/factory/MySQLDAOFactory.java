package com.example.ejemplodaoypatrones.factory;

import com.example.ejemplodaoypatrones.dao.ClienteDAO;
import com.example.ejemplodaoypatrones.dao.FacturaDAO;
import com.example.ejemplodaoypatrones.dao.Factura_ProductoDAO;
import com.example.ejemplodaoypatrones.dao.ProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends AbstractFactory {
    private static MySQLDAOFactory instance = null;

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/entregable";
    public static Connection conn;

    private MySQLDAOFactory() {
    }

    public static synchronized MySQLDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySQLDAOFactory();
        }
        return instance;
    }

    public static Connection createConnection() {
        if (conn != null) {
            return conn;
        }
        String driver = DRIVER;
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAO(createConnection());
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAO(createConnection());
    }

    @Override
    public Factura_ProductoDAO getFactura_ProductoDAO() {
        return new Factura_ProductoDAO(createConnection());
    }

    @Override
    public ProductoDAO getProductoDAO() { return new ProductoDAO(createConnection()); }



//    @Override
//    public PersonaDAO getPersonaDAO() {
//        return new PersonaDAO(createConnection());
//    }
//
//    @Override
//    public DireccionDAO getDireccionDAO() {
//        return new DireccionDAO(createConnection());
//    }
}
