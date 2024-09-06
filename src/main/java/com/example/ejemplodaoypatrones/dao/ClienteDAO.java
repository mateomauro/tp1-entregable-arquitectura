package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements CrudDAO<Cliente> {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente cliente) throws SQLException {
        String query = "INSERT INTO `cliente`(`nombre`, `email`) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,cliente.getNombre());
        ps.setString(2,cliente.getEmail());
        int value = ps.executeUpdate();
        if (value > 0){
            conn.commit();
            System.out.println("fue insertado");
        }
        ps.close();
    }

    // inserta los clientes a la tabla cliente desde el csv
    public void insertClientsCSV() throws SQLException, IOException {
        // hay que cambiarle el path a esto, en mi compu es así pero va a cambiar de cada compu de ustedes
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/clientes.csv"));
        for(CSVRecord row: parser) {
            String query = "INSERT INTO `cliente`(`nombre`, `email`) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,row.get("nombre"));
            ps.setString(2,row.get("email"));
            int value = ps.executeUpdate();
            if (value > 0){
                conn.commit();
                System.out.println("fueron insertados todos los clientes del csv");
            }
            ps.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM cliente WHERE idCliente = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        int value = ps.executeUpdate();
        if (value > 0) {
            conn.commit();
            System.out.println("se ha eliminado el cliente: " + id);
        }else {
            System.out.println("No se ha eliminado porque no existe el cliente con el id: " + id);
        }
        ps.close();
    }

    @Override
    public void update(Cliente cliente, int idCliente) throws SQLException {
        String query = "UPDATE cliente SET nombre = ?, email = ? WHERE idcliente = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getEmail());
        ps.setInt(3, idCliente);
        int value = ps.executeUpdate();
        if (value > 0){
            conn.commit();
            System.out.println("se ha modificado correctamente");
        }
        ps.close();
    }

    @Override
    public Cliente getOne(int id) throws SQLException {
        String query = "SELECT * FROM cliente WHERE idCliente = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();
        Cliente cliente = null;
        if(result.next()){
            cliente = new Cliente(result.getInt(1), result.getString(2), result.getString(3));
        }
        ps.close();
        return cliente;
    }

    @Override
    public ArrayList<Cliente> getAll() throws SQLException {
        String query = "SELECT * FROM cliente";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet clientes = ps.executeQuery();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        while (clientes.next()){
            listaClientes.add(new Cliente(clientes.getInt(1),clientes.getString(2), clientes.getString(3)));
        }
        ps.close();
        return listaClientes;
    }
}
