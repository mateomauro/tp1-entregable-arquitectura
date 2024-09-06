package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO implements CrudDAO<Producto> {
    private Connection conn;

    public ProductoDAO(Connection conn) { this.conn = conn; }


    @Override
    public void insert(Producto producto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Producto producto, int id) throws SQLException {

    }

    @Override
    public Producto getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Producto> getAll() throws SQLException {
        String query = "SELECT * FROM producto";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet productos = ps.executeQuery();
        ArrayList<Producto> listaProductos = new ArrayList<>();
        while (productos.next()){
            listaProductos.add(new Producto(productos.getInt(1),productos.getString(2), productos.getFloat(3)));
        }
        ps.close();
        return listaProductos;
    }
    // Insertar los productos del CSV a la base.
    public void insertProductCSV() throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("C:\\Users\\HP\\Desktop\\ARQUITECTURAS WEB\\TPS\\TP1-Integrador\\tp1-entregable-arquitectura\\src\\main\\resources\\productos.csv"));
        for(CSVRecord row: parser) {
            String query = "INSERT INTO `producto`(`nombre`, `valor`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,row.get("nombre"));
            ps.setString(2,row.get("valor"));
            //System.out.println(row.get("idProducto"));
            //System.out.println(row.get("nombre"));
            //System.out.println(row.get("valor"));
            int value = ps.executeUpdate();
            if (value > 0){
                conn.commit();
                System.out.println("fueron insertados todos los productos del csv");
            }
            ps.close();
        }
    }

    public Producto productoQueMasRecaudo() {
        Producto producto = new Producto();

        return producto;
    }
}
