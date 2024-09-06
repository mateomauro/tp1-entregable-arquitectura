package com.example.ejemplodaoypatrones.utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL() {//Constructor
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/entregable";

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
        } catch (Exception e) {
            e.printStackTrace();
        }
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
//    public void dropTables() throws SQLException {
//        String dropPersona = "DROP TABLE IF EXISTS Persona";
//        this.conn.prepareStatement(dropPersona).execute();
//        this.conn.commit();
//
//        String dropDireccion = "DROP TABLE IF EXISTS Direccion";
//        this.conn.prepareStatement(dropDireccion).execute();
//        this.conn.commit();
//    }

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


//
//    private Iterable<CSVRecord> getData(String archivo) throws IOException {
//        String path = "src\\main\\resources\\" + archivo;
//        Reader in = new FileReader(path);
//        String[] header = {};  // Puedes configurar tu encabezado personalizado aquí si es necesario
//        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);
//
//        Iterable<CSVRecord> records = csvParser.getRecords();
//        return records;
//    }
//
//    public void populateDB() throws Exception {
//        try {
//            System.out.println("Populating DB...");
//            for(CSVRecord row : getData("direcciones.csv")) {
//                if(row.size() >= 4) { // Verificar que hay al menos 4 campos en el CSVRecord
//                    String idString = row.get(0);
//                    String numeroString = row.get(3);
//                    if(!idString.isEmpty() && !numeroString.isEmpty()) {
//                        try {
//                            int id = Integer.parseInt(idString);
//                            int numero = Integer.parseInt(numeroString);
//                            Direccion direccion = new Direccion(id, row.get(1), row.get(2), numero);
//                            insertDireccion(direccion, conn);
//                        } catch (NumberFormatException e) {
//                            System.err.println("Error de formato en datos de dirección: " + e.getMessage());
//                        }
//                    }
//                }
//            }
//            System.out.println("Direcciones insertadas");
//
//            for (CSVRecord row : getData("personas.csv")) {
//                if (row.size() >= 4) { // Verificar que hay al menos 4 campos en el CSVRecord
//                    String idString = row.get(0);
//                    String nombre = row.get(1);
//                    String edadString = row.get(2);
//                    String idDireccionString = row.get(3);
//
//                    if (!idString.isEmpty() && !nombre.isEmpty() && !edadString.isEmpty() && !idDireccionString.isEmpty()) {
//                        try {
//                            int id = Integer.parseInt(idString);
//                            int edad = Integer.parseInt(edadString);
//                            int idDireccion = Integer.parseInt(idDireccionString);
//
//                            Persona persona = new Persona(id, nombre, edad, idDireccion);
//                            insertPersona(persona, conn);
//                        } catch (NumberFormatException e) {
//                            System.err.println("Error de formato en datos de persona: " + e.getMessage());
//                        }
//                    }
//                }
//            }
//
//            System.out.println("Personas insertadas");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private int insertPersona (Persona persona, Connection conn) throws Exception{
//        String insert = "INSERT INTO Persona (idPersona, nombre, edad,idDireccion) VALUES (?, ?, ?,?)";
//        PreparedStatement ps = null;
//        try {
//            ps = conn.prepareStatement(insert);
//            ps.setInt(1,persona.getIdPersona());
//            ps.setString(2, persona.getNombre());
//            ps.setInt(3,persona.getEdad());
//            ps.setInt(4,persona.getIdDireccion());
//            if (ps.executeUpdate() == 0) {
//                throw new Exception("No se pudo insertar");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closePsAndCommit(conn, ps);
//        }
//        return 0;
//    }
//
//
//    private int insertDireccion(Direccion direccion, Connection conn) throws Exception {
//
//        String insert = "INSERT INTO Direccion (idDireccion, ciudad, calle,numero) VALUES (?, ?, ?,?)";
//        PreparedStatement ps = null;
//        try {
//            ps = conn.prepareStatement(insert);
//            ps.setInt(1,direccion.getIdDireccion());
//            ps.setString(2, direccion.getCiudad());
//            ps.setString(3,direccion.getCalle());
//            ps.setInt(4,direccion.getNumero());
//            if (ps.executeUpdate() == 0) {
//                throw new Exception("No se pudo insertar");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            closePsAndCommit(conn, ps);
//        }
//        return 0;
//    }

    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


