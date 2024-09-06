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
        String query = "INSERT INTO producto (nombre, valor) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, producto.getNombre());
        ps.setFloat(2, producto.getValor());
        int value = ps.executeUpdate();
        if(value > 0){
            conn.commit();
            System.out.println("Se inserto");
        }
        ps.close();
    }


    // Insertar los productos del CSV a la base.
    public void insertProductCSV() throws SQLException, IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/productos.csv"));
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

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM producto WHERE idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        int value = ps.executeUpdate();
        if(value > 0){
            conn.commit();
            System.out.println("se borro el id "+id);
        }
        ps.close();
    }

    @Override
    public void update(Producto producto, int id) throws SQLException {
        String query = "UPDATE producto set nombre = ?, valor = ? WHERE idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,producto.getNombre());
        ps.setFloat(2,producto.getValor());
        ps.setInt(3, id);
        int value = ps.executeUpdate();
        if(value > 0){
            conn.commit();
            System.out.println("Se modifico correctamente el id "+id);
        }
        ps.close();
    }

    @Override
    public Producto getOne(int id) throws SQLException {
        String query = "SELECT * FROM producto WHERE idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Producto producto = null;
        if(rs.next()){
            producto = new Producto(rs.getInt(1), rs.getString(2) ,rs.getFloat(3));
            conn.commit();
        }
        return producto;
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


    public Producto productoQueMasRecaudo() {
        Producto producto = new Producto();

        return producto;
    }
}
