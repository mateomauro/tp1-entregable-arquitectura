package com.example.integrador.dao;

import com.example.integrador.entities.Factura;
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

public class FacturaDAO implements CrudDAO<Factura>{

    private Connection conn;

    public FacturaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Factura factura) throws SQLException {
        String query = "INSERT INTO `factura` (`idCliente`) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, factura.getIdCliente());
        int value = ps.executeUpdate();
        if(value > 0 ){
            conn.commit();
            System.out.println("Factura agregada com exito");
        }
        ps.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM `factura` WHERE idFactura = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        int value = ps.executeUpdate();
        if (value > 0) {
            conn.commit();
            System.out.println("se ha eliminado la factura: " + id);
        }else {
            System.out.println("No se ha eliminado porque no existe la factura con el id: " + id);
        }
        ps.close();
    }

    @Override
    public void update(Factura factura, int id) throws SQLException {
        String query = "UPDATE factura SET idCliente = ? WHERE idFactura = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, factura.getIdCliente());
        ps.setInt(2, factura.getIdFactura());
        int value = ps.executeUpdate();
        if (value > 0){
            conn.commit();
            System.out.println("se ha modificado correctamente la factura");
        }
        ps.close();
    }

    @Override
    public Factura getOne(int id) throws SQLException {
        String query = "SELECT * FROM factura WHERE idFactura = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        Factura factura = null;
        if(result.next()){
            factura = new Factura(result.getInt(1), result.getInt(2));
        }
        ps.close();
        return factura;
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
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(
                new FileReader("src/main/resources/facturas.csv"));
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
