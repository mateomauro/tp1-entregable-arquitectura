package com.example.ejemplodaoypatrones;

import com.example.ejemplodaoypatrones.dao.ClienteDAO;
import com.example.ejemplodaoypatrones.entities.Cliente;
import com.example.ejemplodaoypatrones.factory.AbstractFactory;
import com.example.ejemplodaoypatrones.utils.HelperMySQL;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        HelperMySQL dbMySQL = new HelperMySQL();
        //dbMySQL.dropTables();
        dbMySQL.createTables();

//        dbMySQL.populateDB();
//        dbMySQL.closeConnection();
//
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
//        System.out.println();
//        System.out.println("////////////////////////////////////////////");
//        System.out.println("////////////////////////////////////////////");
//        System.out.println();
        ClienteDAO cliente = chosenFactory.getClienteDAO();

        cliente.insert(new Cliente("mateo", "seRompio@hotmail.com"));
        cliente.delete(2);
        cliente.update(new Cliente("tomas", "tomas@gmail.com"), 4);

        Cliente clienteTomas = cliente.getOne(4);
        System.out.println(clienteTomas);
        ArrayList<Cliente> listaClientes = cliente.getAll();
        System.out.println(listaClientes);
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
