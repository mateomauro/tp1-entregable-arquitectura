package com.example.ejemplodaoypatrones.dao;

import com.example.ejemplodaoypatrones.entities.Cliente;

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
