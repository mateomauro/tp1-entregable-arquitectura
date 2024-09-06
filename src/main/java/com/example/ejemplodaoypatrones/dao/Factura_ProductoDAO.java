package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Factura_Producto;
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
import java.util.List;

public class Factura_ProductoDAO implements CrudDAO<Factura_Producto> {

    private Connection conn;

    public Factura_ProductoDAO(Connection conn) { this.conn = conn; }

    @Override
    public void insert(Factura_Producto facturaProducto) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Factura_Producto facturaProducto, int id) throws SQLException {

    }

    @Override
    public Factura_Producto getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Factura_Producto> getAll() throws SQLException {
        String query = "SELECT * FROM factura_producto";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet factura_producto = ps.executeQuery();
        ArrayList<Factura_Producto> lista_facturas_productos = new ArrayList<>();
        while (factura_producto.next()){
            lista_facturas_productos.add(new Factura_Producto(factura_producto.getInt(1),factura_producto.getInt(2), factura_producto.getInt(3)));
        }
        ps.close();
        return lista_facturas_productos;
    }

    // Inserta los productos que fueron vendidos ( o sea, que pertenecen a una factura ) a la base.
    public void insertFactura_ProductoCSV() throws SQLException, IOException {
        // hay que ver acá el tema de la ruta
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("C:\\Users\\HP\\Desktop\\ARQUITECTURAS WEB\\TPS\\TP1-Integrador\\tp1-entregable-arquitectura\\src\\main\\resources\\facturas-productos.csv"));
        for(CSVRecord row: parser) {
            String query = "INSERT INTO `factura_producto`(`idFactura`, `idProducto`, `cantidad`) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(row.get("idFactura")));
            ps.setInt(2, Integer.parseInt(row.get("idProducto")));
            ps.setInt(3, Integer.parseInt(row.get("cantidad")));

            //System.out.println(row.get("idProducto"));
            //System.out.println(row.get("nombre"));
            //System.out.println(row.get("valor"));
            int value = ps.executeUpdate();
            if (value > 0){
                conn.commit();
                System.out.println("fueron insertados todos los facturas_productos del csv en la tabla facturas_productos");
            }
            ps.close();
        }
    }
}
