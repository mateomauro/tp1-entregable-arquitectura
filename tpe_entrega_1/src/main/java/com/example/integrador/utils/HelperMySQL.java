package com.example.integrador.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        // En esta URI se podría haber puesto el nombre de la base de datos pero lo implementamos para
        // que se cree automáticamente al ejecutar el programa.
        String uri = "jdbc:mysql://localhost:3306/";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            this.conn = DriverManager.getConnection(uri, "root", "");
            this.conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDatabase() throws SQLException {
    String nameDB = "entregable";
    String createDatabase = "CREATE DATABASE IF NOT EXISTS " + nameDB;
    // aquí volvemos a pisar la URI con el nombre de la base de datos y pedimos nuevamente la conexión.
    try (Statement stmt = this.conn.createStatement()) {
        stmt.execute(createDatabase);
        this.conn.commit();
        String uri = "jdbc:mysql://localhost:3306/" + nameDB;
        this.conn = DriverManager.getConnection(uri, "root", "");
        this.conn.setAutoCommit(false);
    } catch (SQLException e) {
        System.err.println("Error al crear la base de datos: " + e.getMessage());
    }
}

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS cliente(" +
                "idCliente INT NOT NULL AUTO_INCREMENT, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente));";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();

        String tableFactura = "CREATE TABLE IF NOT EXISTS factura(" +
                "idFactura INT NOT NULL AUTO_INCREMENT, " +
                "idCliente INT NOT NULL, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
                "CONSTRAINT FK_cliente FOREIGN KEY (idCliente) REFERENCES cliente (idCliente));";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS producto(" +
                "idProducto INT NOT NULL AUTO_INCREMENT, " +
                "nombre VARCHAR(45) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();

        String tableFactura_Producto = "CREATE TABLE IF NOT EXISTS factura_producto(" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "CONSTRAINT Factura_Producto_pk PRIMARY KEY (idFactura, idProducto), " +
                "CONSTRAINT FK_Factura_Producto FOREIGN KEY (idProducto) REFERENCES producto (idProducto), " +
                "CONSTRAINT FK_Factura FOREIGN KEY (idFactura) REFERENCES factura (idFactura));";
        this.conn.prepareStatement(tableFactura_Producto).execute();
        this.conn.commit();
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


