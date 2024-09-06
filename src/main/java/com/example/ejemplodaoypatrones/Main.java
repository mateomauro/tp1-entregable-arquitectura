package com.example.ejemplodaoypatrones;

import com.example.ejemplodaoypatrones.dao.ClienteDAO;
import com.example.ejemplodaoypatrones.dao.FacturaDAO;
import com.example.ejemplodaoypatrones.dao.Factura_ProductoDAO;
import com.example.ejemplodaoypatrones.dao.ProductoDAO;
import com.example.ejemplodaoypatrones.entities.Cliente;
import com.example.ejemplodaoypatrones.entities.Factura;
import com.example.ejemplodaoypatrones.entities.Factura_Producto;
import com.example.ejemplodaoypatrones.entities.Producto;
import com.example.ejemplodaoypatrones.factory.AbstractFactory;
import com.example.ejemplodaoypatrones.utils.HelperMySQL;

import java.util.ArrayList;
import com.example.ejemplodaoypatrones.dao.Factura_ProductoDAO;
import com.example.ejemplodaoypatrones.entities.Factura_Producto;

public class Main {

    public static void main(String[] args) throws Exception {
        HelperMySQL dbMySQL = new HelperMySQL();
        //dbMySQL.dropTables();
        dbMySQL.createTables();

        //dbMySQL.populateDB();
        //dbMySQL.closeConnection();
//
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
//        System.out.println();
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("////////////////////////////////////////////");
//        System.out.println();
        ClienteDAO cliente = chosenFactory.getClienteDAO();
        FacturaDAO factura = chosenFactory.getFacturaDAO();
        Factura_ProductoDAO factura_producto = chosenFactory.getFactura_ProductoDAO();
        ProductoDAO producto = chosenFactory.getProductoDAO();
        //cliente.insert(new Cliente("mateo", "seRompio@hotmail.com"));
        //cliente.delete(2);
        //cliente.update(new Cliente("tomas", "tomas@gmail.com"), 4);
         cliente.insertClientsCSV();
        producto.insertProductCSV();
        factura.insertFacturaCSV();
        factura_producto.insertFactura_ProductoCSV();
        //Cliente clienteTomas = cliente.getOne(4);
        //System.out.println(clienteTomas);
//        ArrayList<Cliente> listaClientes = cliente.getAll();
//        System.out.println("Lista clientes: " + listaClientes);
//        System.out.println();
//
//        ArrayList<Factura> listaFactura = factura.getAll();
//        System.out.println("Lista factura: " + listaFactura);
//        System.out.println();
//
//        ArrayList<Factura_Producto> listaFactura_Productos = factura_producto.getAll();
//        System.out.println("Lista factura_productos: " + listaFactura_Productos);
//        System.out.println();
//
//        ArrayList<Producto> listaProductos = producto.getAll();
//        System.out.println("Lista productos: " + listaProductos);

//
//
//        System.out.println("Busco una Persona por id: ");
//        Persona personaById = persona.find(2);
//        System.out.println(personaById);
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("Lista de direcciones: ");
////        List<Direccion> listadoDirecciones = direccion.selectList();
////        System.out.println(listadoDirecciones);
//        List<Direccion> listadoDirecciones = direccion.selectList();
//        for (Direccion dir : listadoDirecciones) {
//            System.out.println(dir);
//        }
//
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("////////////////////////////////////////////");
//
////        Persona p = new Persona(6,"Sergio",50,2);
////        persona.insertPersona(p);
//
//        PersonaDTO personaDTO = persona.findPersonaDTO(2);
//        System.out.println(personaDTO);

    }
}
