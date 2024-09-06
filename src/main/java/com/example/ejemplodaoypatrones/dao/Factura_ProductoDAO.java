package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Factura_Producto;
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

    public Factura_ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Factura_Producto factura_producto) throws SQLException {
        String query = "INSERT INTO Factura_ProductoDAO(idFactura,idProducto, cantidad) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, factura_producto.getIdFactura());
        ps.setInt(2, factura_producto.getIdProducto());
        ps.setInt(3, factura_producto.getCantidad());

        int value = ps.executeUpdate();
        if (value > 0){
            conn.commit();
            System.out.println("fue insertada la Factura_Producto" + factura_producto);
        }
        ps.close();
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

    @Override
    public void delete(int id) throws SQLException { }

    public void delete(int idFactura, int idProducto) throws SQLException {
        String query = "DELETE FROM Factura_Producto WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        int value = ps.executeUpdate();
        if (value > 0) {
            conn.commit();
            System.out.println("se ha eliminado la Factura_Producto con id: " + idFactura + "y " + idProducto);
        }else {
            System.out.println("No se ha eliminado porque no existe la Factura_Producto con el id: " + idFactura + "y " + idProducto);
        }
        ps.close();
    }

    @Override
    public void update(Factura_Producto factura_producto, int idFacturaProducto) throws SQLException { }

    public void update(Factura_Producto factura_producto, int idFactura, int idProducto) throws SQLException {
        String query = "UPDATE Factura_Producto SET idFactura = ?, idProducto = ?, cantidad = ? WHERE idFactura = ? AND idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        ps.setInt(3, factura_producto.getCantidad());

        int value = ps.executeUpdate();
        if (value > 0){
            conn.commit();
            System.out.println("se ha modificado correctamente la Factura_Producto con id: " + idFactura + "y " + idProducto);
        }
        ps.close();
    }

    @Override
    public Factura_Producto getOne(int id) throws SQLException {
        String query = "SELECT * FROM Factura_Producto WHERE idFactura = ? and idProducto = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        Factura_Producto facturaProducto = null;
        if(result.next()){
            facturaProducto = new Factura_Producto(result.getInt(1), result.getInt(2), result.getInt(3));
        }
        ps.close();
        return facturaProducto;
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


}
