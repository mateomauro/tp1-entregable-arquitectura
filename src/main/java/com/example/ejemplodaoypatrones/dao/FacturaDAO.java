package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Factura;
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

public class FacturaDAO implements CrudDAO<Factura> {
    private Connection conn;

    public FacturaDAO(Connection conn) { this.conn = conn; }


    @Override
    public void insert(Factura factura) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public void update(Factura factura, int id) throws SQLException {

    }

    @Override
    public Factura getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Factura> getAll() throws SQLException {
        String query = "SELECT * FROM factura";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet factura = ps.executeQuery();
        ArrayList<Factura> lista_facturas = new ArrayList<>();
        while (factura.next()){
            lista_facturas.add(new Factura(factura.getInt(1),factura.getInt(2)));
        }
        ps.close();
        return lista_facturas;
    }

    // Inserta los productos que fueron vendidos ( o sea, que pertenecen a una factura ) a la base.
    public void insertFacturaCSV() throws SQLException, IOException {
        // hay que ver acá el tema de la ruta
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new
                FileReader("C:\\Users\\HP\\Desktop\\ARQUITECTURAS WEB\\TPS\\TP1-Integrador\\tp1-entregable-arquitectura\\src\\main\\resources\\facturas.csv"));
        for(CSVRecord row: parser) {
            String query = "INSERT INTO `factura` (`idFactura`, `idCliente`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(row.get("idFactura")));
            ps.setInt(2, Integer.parseInt(row.get("idCliente")));

            //System.out.println(row.get("idProducto"));
            //System.out.println(row.get("nombre"));
            //System.out.println(row.get("valor"));
            int value = ps.executeUpdate();
            if (value > 0){
                conn.commit();
                System.out.println("fueron insertados todos las facturas del csv en la tabla factura");
            }
            ps.close();
        }
    }
}
